var flag = false;

function preload(){
    var preload = new Image();
    preload.src = "Images/play button 2.png";
}

function mudaImagem(){
    if (!flag)
        document.getElementById('play').src = "Images/play button 2.png";
    else
        document.getElementById('play').src = "Images/play button.png";

    flag = !flag;
}