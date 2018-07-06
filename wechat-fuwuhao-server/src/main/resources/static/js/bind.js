//接口路径
const apiUrl = "http://127.0.0.1:8080/";

//倒计时的剩余时间，用于存入cookie的键
const COOKIE_TIME = "remainSecond";

$('#btnVcode').on('click', function() {
    const $this = $(this);
    if($this.hasClass('code-active')) {
        //还在倒计时，不能重复发送
    }else {
        //调用发送验证码接口
        const phoneNumber = $("#phoneNumber").val();
        if (isMobile(phoneNumber)) {
            $.ajax({
                type: "GET",
                url: apiUrl + 'wx/common?functionId=fetchAuthCode&phoneNumber=' + phoneNumber,
                dataType: 'json',
                data: {},
                success: function(data){
                    if (typeof(data) === 'string') {
                        data = JSON.parse(data);
                    }
                    //0:发送成功，1：失败
                    const code = data.code;
                    if (code === 0 || code === '0') {
                        //在此处获取验证码ajax
                        $this.addClass('code-active');
                        setVTime(30);
                    }else {
                        showAlert('获取验证码失败',2000);
                    }
                },
                error: function(data){}
            })
        }else {
            //提示输入正确的手机号码
            showAlert('请输入正确的手机号码',2000);
        }
    }
});

/**
 * 显示提示
 * @param msg
 * @param second
 */
function showAlert(msg, second) {
    const error = $("#errorTips");
    error.text(msg);
    error.show();
    setTimeout(
        function () {
            const error = $("#errorTips");
            error.text("");
            error.hide();
        },second
    )
}

/**
 * 倒计时
 * @param second 剩余秒数
 */
function setVTime(second) {
    const obj = $("#btnVcode");
    second -= 1;
    obj.html(second + 's之后重新获取');
    $.cookie(COOKIE_TIME, Math.floor(new Date().getTime()/1000) + second);
    if(second <= 0) {
        obj.removeClass('code-active');
        obj.html('获取验证码');
        return;
    }
    setTimeout(function() {
        setVTime(second);
    }, 1000);
}

/**
 * 隐藏提示
 */
function hideAlert() {
    const error = $("#errorTips");
    error.text("");
    error.hide();
}

/**
 * 是否手机
 * @param mobile
 * @returns {boolean}
 */
function isMobile(mobile) {
   if (mobile && mobile.length === 11 && !isNaN(mobile)) {
       return true;
   }else {
       console.log("*** !isNaN(mobile) = " + !isNaN(mobile));
       return false;
   }
}

/**
 * 点击绑定按钮
 * /wx/bind?phoneNumber=13738053603&authCode=1363
 */
function bindWeChat() {
    const phoneNumber = $("#phoneNumber").val();
    if (!isMobile(phoneNumber)) {
        showAlert('请填写正确的手机',2000);
        return;
    }
    const authCode = $("#authCode").val();
    if (!authCode) {
       showAlert('请输入验证码',2000);
       return;
    }
    $.ajax({
        type: "GET",
        url: apiUrl + 'wx/bind?phoneNumber=' + phoneNumber +'&authCode=' + authCode,
        dataType: 'json',
        data: {},
        success: function(data){
            if (typeof(data) === 'string') {
                data = JSON.parse(data);
            }
            const code = data.code;
            const msg = data.msg;
            //0:成功重定向，1：验证码错误/失效，-1：手机号或者验证码空，
            if (code === 1 || code === '1' || code === -1 || code === -1 || code === 3 || code === '3') {
                showAlert(msg,2000);
            }
        },
        error: function(data){}
    })
}

/**
 * 页面加载完毕之后
 * 1.先去cookie中获取倒计时的时间，及剩余时间，判断上次的时间是否还没有过
 */
$(function () {
    //过期的秒
    const expireTime = $.cookie(COOKIE_TIME);
    const obj = $("#btnVcode");
    //如果之前发送过验证码,则需要判断是否再90s内重复发送
    if (expireTime){
        //当前的秒
        const nowTime = Math.floor(new Date().getTime()/1000);
        //已经超过了
        if (nowTime >= expireTime) {
            obj.removeClass('code-active');
        }else {
            //还在90秒之内，则不能发送,且按钮继续显示倒计时
            obj.addClass('code-active');
            setVTime(expireTime - nowTime);
        }
    }
});