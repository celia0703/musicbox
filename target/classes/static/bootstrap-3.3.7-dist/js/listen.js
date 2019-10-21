

window.onload=function () {
    /**
     * 显示播放
     */
        var btn1 = document.getElementsByClassName('btn1')[0];
            btn1.addEventListener('click',function () {
            btn1.getElementById('light1').style.display='block';
            document.getElementById('fade1').style.display= 'block';
        })
        var btn2 = document.getElementsByClassName('btn2')[0];
        btn2.addEventListener('click',function () {
            document.getElementById('light2').style.display= 'block';
            document.getElementById('fade2').style.display= 'block';
        })
        var btn3 = document.getElementsByClassName('btn3')[0];
        btn3.addEventListener('click',function () {
            document.getElementById('light3').style.display= 'block';
            document.getElementById('fade3').style.display= 'block';
        })
        var btn4 = document.getElementsByClassName('btn4')[0];
        btn4.addEventListener('click',function () {
            document.getElementById('light4').style.display= 'block';
            document.getElementById('fade4').style.display= 'block';
        })
        /**
         * 关闭
         */
        var exit1 = document.getElementsByClassName('btnExit1')[0];
        exit1.addEventListener('click',function(){
            document.getElementById('light1').style.display='none';
            document.getElementById('fade1').style.display='none';
        })
        var exit2 = document.getElementsByClassName('btnExit2')[0];
        exit2.addEventListener('click',function(){
            document.getElementById('light2').style.display= 'none';
            document.getElementById('fade2').style.display= 'none';
        })
        var exit3 = document.getElementsByClassName('btnExit3')[0];
        exit3.addEventListener('click',function(){
            document.getElementById('light3').style.display= 'none';
            document.getElementById('fade3').style.display= 'none';
        })
        var exit4 = document.getElementsByClassName('btnExit4')[0];
        exit4.addEventListener('click',function(){
            document.getElementById('light4').style.display= 'none';
            document.getElementById('fade4').style.display= 'none';
        })
    }
