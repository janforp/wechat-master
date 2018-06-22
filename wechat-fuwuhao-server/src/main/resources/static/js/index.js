function keyHandler(evt){
    if (evt.keyCode === 13) {
        generateQrCode();
    }
}

/**
 * 点击生成二维码按钮
 */
function generateQrCode() {
    //获取渠道号
    let channel = getQueryPara('channel');
    if(!channel) {
        alert('请在地址栏中输入?channel=渠道号');
        return;
    }

    const phone = $("#phone").val();
    if (!isValidMobile(phone)) {
        alert("请输入正确的手机号码");
        return;
    }
    $("#phoneSpan").text(phone);

    //扫码下载的二维码
    const requestUrl =  '/qr/generate/' + phone + '/' +channel;
    const tuijianQr = "<img src='"+  requestUrl + "'>";
    $("#tuijianQr").html(tuijianQr);

    //扫码统计的二维码
    const statisticsUrl =  '/qr/generateStatisticsQr/' + phone + '/' +channel;
    const statisticsQr = "<img src='"+  statisticsUrl + "'>";
    $("#statisticsQr").html(statisticsQr);

    //推广二维码地址
    const downloadImageUrl = baseUrl + requestUrl;
    $("#downloadSpan").text(downloadImageUrl);
    const statisticsQrUrl = baseUrl + '/statisticsPage/' + phone + '/' + channel;
    $("#statisticsSpan").text(statisticsQrUrl);

    $("#desc").show();
}