var click = false;
var objClicado = null;
var resultado = "";
var mudaComp = false;
var pronto = false;
var acabouJogo = false;
var pontosJ = 0, pontosC = 0; 
var audio = null;

function preload(){
    img = new Array("Images/Pedra2 - pixel.png", "Images/Papel2 - pixel.png", "Images/Tesoura2 - pixel.png", "Images/Pronto.png", "Images/Pronto 2.png", "Images/Reiniciar.png", "Images/Reiniciar2.png", "Images/Venceu.png", "Images/Perdeu.png");

    for (i = 0; i < img.length; i++) {
        var preload = new Image();
        preload.src = img[i];
    }
}

function somDeMudanca(){
    audio = document.getElementById('somMudanca');
    ativaSom();
}

function somDeClickBotao(){
    audio = document.getElementById('somClick');
    ativaSom();
}

function somDeVitoria(){
    audio = document.getElementById('VitorySound');
    ativaSom();
}

function somDeDerrota(){
    audio = document.getElementById('LoseSound');
    ativaSom();
}

function ativaSom(){
    audio.volume = 0.3;
    audio.play();
}

function trocaImagem(obj, tipo){
    if (!click) {
        switch (tipo){
            case 1: obj.src = "Images/Pedra2 - pixel.png";
            if (!mudaComp) {
                document.getElementById('papelj').src = "Images/Papel - pixel.png";
                document.getElementById('tesouraj').src = "Images/Tesoura - pixel.png";
            }
            break;

            case 2: obj.src = "Images/Papel2 - pixel.png";
            if (!mudaComp) {
                document.getElementById('pedraj').src = "Images/Pedra - pixel.png";
                document.getElementById('tesouraj').src = "Images/Tesoura - pixel.png";
            }
            break;

            case 3: obj.src = "Images/Tesoura2 - pixel.png";
            if (!mudaComp) {
                document.getElementById('papelj').src = "Images/Papel - pixel.png";
                document.getElementById('pedraj').src = "Images/Pedra - pixel.png";
            }
            break;
        }
    }
}

function limpa(){
    if (!click) {
        document.getElementById('pedraj').src = "Images/Pedra - pixel.png";
        document.getElementById('papelj').src = "Images/Papel - pixel.png";
        document.getElementById('tesouraj').src = "Images/Tesoura - pixel.png";
        document.getElementById('pedrac').src = "Images/Pedra - pixel.png";
        document.getElementById('papelc').src = "Images/Papel - pixel.png";
        document.getElementById('tesourac').src = "Images/Tesoura - pixel.png";
    }
}

function clicado(obj, tipo){
    if (!pronto) {
        if (obj !== objClicado) {
            click = false;
            trocaImagem(obj, tipo);
            click = true;
            objClicado = obj;
            mudaButtonPronto();
            somDeMudanca();
        }
    }
}

function mudaEstadoDaImagemFinal(el) {
    var display = document.getElementById(el).style.display;
    if (display == "none")
        document.getElementById(el).style.display = 'block';
    else
        document.getElementById(el).style.display = 'none';
}

function vencedor(){
    if ((pontosJ == 2 && pontosC == 0) || pontosJ == 3){
        somDeVitoria();
        mudaEstadoDaImagemFinal('venceuIMG');
        acabouJogo = true;
    }

    if ((pontosJ == 0 && pontosC == 2) || pontosC == 3){
        somDeDerrota();
        mudaEstadoDaImagemFinal('perdeuIMG');
        acabouJogo = true;
    }

    if (acabouJogo) mudaButtonReiniciar();
}

function rodada(){
    if (resultado == "empate") {
        alert("Empate!");
    } else {
        if (resultado == "jog") {
            alert("Você venceu!");
            document.getElementById('PointJog').innerHTML = ++pontosJ;
        } else {
            alert("Você pedeu!");
            document.getElementById('PointComp').innerHTML = ++pontosC;
        }
    }
    pronto = true;
    setTimeout("reiniciaRodada()", 500);

    if (pontosC > 1 || pontosJ > 1)
        setTimeout("vencedor()", 700);
}

function mudaImagemDoComputador(comp){
    mudaComp = true;
    click = false;
    switch (comp){
        case 1: trocaImagem(document.getElementById('pedrac'), 1);
        break;
        case 2: trocaImagem(document.getElementById('papelc'), 2);
        break;
        case 3: trocaImagem(document.getElementById('tesourac'), 3);
        break;
    }
    click = true;
}

function operacaoDoJogo(){
    if (!pronto && !acabouJogo) {
        if (click) {
            somDeClickBotao();
            var comp = Math.floor(Math.random() * 4 + 1);
            mudaImagemDoComputador(comp);

            if (objClicado === document.getElementById('pedraj')){
                if (comp == 1) resultado = "empate";
                if (comp == 2) resultado = "comp";
                if (comp == 3) resultado = "jog";
            }
            else {
                if (objClicado === document.getElementById('papelj')){
                    if (comp == 1) resultado = "jog";
                    if (comp == 2) resultado = "empate";
                    if (comp == 3) resultado = "comp";
                }
                else {
                    if (comp == 1) resultado = "comp";
                    if (comp == 2) resultado = "jog";
                    if (comp == 3) resultado = "empate";
                }
            }
            setTimeout("rodada()", 1300);
        } else {
            alert("Escolha uma opção!");
        }
    }
}

function reiniciaRodada(){
    click = false;
    objClicado = null;
    resultado = "";
    mudaComp = false;
    pronto = false;
    limpa();
    document.getElementById('Pronto').src = "Images/Pronto apagado.png";
}

function reiniciaJogo(){
    if (acabouJogo) {
        reiniciaRodada();
        somDeClickBotao();
        document.getElementById('PointJog').innerHTML = 0;
        document.getElementById('PointComp').innerHTML = 0;
        document.getElementById('Reiniciar').src = "Images/Reiniciar apagado.png";
        acabouJogo = false;
        if (pontosJ > pontosC)
            mudaEstadoDaImagemFinal("venceuIMG");
        else
            mudaEstadoDaImagemFinal("perdeuIMG");

        pontosC = 0;
        pontosJ = 0;
    }
}

function mudaButtonPronto(){
    if (click)
        document.getElementById('Pronto').src = "Images/Pronto.png";
}

function buttonProntoSelecionado(){
    if (click)
        document.getElementById('Pronto').src = "Images/Pronto 2.png";               
}

function mudaButtonReiniciar(){
    if (acabouJogo)
        document.getElementById('Reiniciar').src = "Images/Reiniciar.png";
}

function buttonReiniciarSelecionado(){
    if (acabouJogo)
        document.getElementById('Reiniciar').src = "Images/Reiniciar2.png";               
}