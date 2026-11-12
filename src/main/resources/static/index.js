listar();
const btnIncluir = document.getElementById("incluir");
const erroNome = document.getElementById("erroNomeAlterar");
const erroCamisa = document.getElementById("erroNumeroCamisaAlterar");
const erroLesionado = document.getElementById("erroLesionadoAlterar");
const erroTime = document.getElementById("erroTimeAlterar");
const modalFechar = document.getElementById("fecharModalAlterar");
const modalAlterar = document.getElementById("modalAlterar");
btnIncluir.addEventListener("click", redirecionar);

modalFechar.addEventListener("click", function () {
    modalAlterar.style.display = "none";
})

function redirecionar() {
    window.location.href = 'incluir.html';
}

// Função para listar todos jogadores.
async function listar() {
    const response = await fetch('/jogadores');

    if (!response.ok) {
        alert("Error, Status: " + response.status);
    } else {
        const listaJogadores = document.getElementById("listaJogadores");
        listaJogadores.innerText = "";
        const jogadores = await response.json();
        if (jogadores.length > 0) {
            for (const j of jogadores) {
                const divJogador = document.createElement("div");
                divJogador.classList.add("jogador");
                const divInfo = document.createElement("div");
                divInfo.classList.add("informacoes");
                divJogador.append(divInfo);
                const pCamisa = document.createElement("p");
                const pTime = document.createElement("p");
                const divNome = document.createElement("div");
                divNome.classList.add("nome-jogador");

                const nome = document.createElement("h3");
                nome.textContent = j.nome;

                divNome.append(nome);

                if (j.lesionado === true) {
                    const lesionado = document.createElement("p");
                    lesionado.textContent = "LESIONADO";
                    lesionado.classList.add("lesionado");

                    divNome.append(lesionado);
                }
                divInfo.append(divNome);
                const valorCamisa = document.createElement("span");
                valorCamisa.textContent = j.numeroCamisa;
                pCamisa.textContent = "Camisa: ";
                pCamisa.append(valorCamisa);
                divInfo.append(pCamisa);
                const valorTime = document.createElement("span");
                valorTime.textContent = j.time.nome;
                pTime.textContent = "Time: ";
                pTime.append(valorTime);
                divInfo.append(pTime);

                const divAcoes = document.createElement("div");
                divAcoes.classList.add("acoes");
                divJogador.append(divAcoes);
                const btnAlterar = document.createElement("button");
                btnAlterar.textContent = "Alterar";
                btnAlterar.dataset.id = j.id;
                btnAlterar.classList.add("btn-alterar");
                // implementação para chamar a função correspondente ao botão clicado
                // passando o id dentro do data-set no botão.
                btnAlterar.addEventListener("click", alterarJogador);
                divAcoes.append(btnAlterar);
                const btnExcluir = document.createElement("button");
                btnExcluir.textContent = "Excluir";
                btnExcluir.dataset.id = j.id;
                btnExcluir.addEventListener("click", deleteJogador);
                btnExcluir.classList.add("btn-excluir");
                divAcoes.append(btnExcluir);
                listaJogadores.append(divJogador);
            }
        } else {
            listaJogadores.innerText = "Nenhum jogador cadastrado.";
        }
    }
}

//                    Modal de cadastro de time
const btnCadastrarTime = document.getElementById("cadastrarTime");
const modalTime = document.getElementById("modalTime");
const btnFecharModal = document.getElementById("fecharModal");
const btnCancelarModal = document.getElementById("cancelarModal");

btnCadastrarTime.addEventListener("click", function () {
    modalTime.style.display = "flex";
});

btnFecharModal.addEventListener("click", fecharModal);
btnCancelarModal.addEventListener("click", fecharModal);

function fecharModal() {
    modalTime.style.display = "none";
}


// Função para criar time
const formTime = document.getElementById("formTime");

formTime.addEventListener("submit", async function (evt) {
    evt.preventDefault();

    const nome = document.getElementById("nomeTime");

    const time = {
        nome: nome.value
    }

    const response = await fetch("/times", {
        method: "POST",
        body: JSON.stringify(time),
        headers: {
            "Content-Type": "application/json"
        }
    })
    const nomeTimeErro = document.getElementById("erroNomeTime");
    nomeTimeErro.innerText = "";
    if (response.status === 400) {
        const erro = await response.json();

        nomeTimeErro.innerText = erro.nome;
        return;
    } else if (!response.ok) {
        alert("Não foi possível cadastrar o time, Status: " + response.status);
    } else {
        nome.value = "";
        alert("Time cadastrado com sucesso!")
        fecharModal();
        window.location.href = "index.html";
    }
})


// Função de alterar Jogador e mostrar os dados do jogador selecionado.

let jogadorAlterado;

async function alterarJogador() {
    modalAlterar.style.display = "flex";
    const nomeAlterar = document.getElementById("nomeAlterar");
    const numeroCamisa = document.getElementById("numeroCamisaAlterar");
    const timesList = document.getElementById("timeAlterar");
    const radiosLesionado = document.querySelectorAll('input[name="lesionado"]');
    const descricao = document.getElementById("descricaoAlterar");
    const idJogador = this.dataset.id;
    jogadorAlterado = idJogador;
    const tituloAlterar = document.getElementById("tituloAlterar");
    tituloAlterar.innerText = "Alterar jogador - ID: " + idJogador;

    erroNome.innerText = "";
    erroCamisa.innerText = "";
    erroLesionado.innerText = "";
    erroTime.innerText = "";

    const responseGetJogador = await fetch(`/jogadores/${idJogador}`);
    if (!responseGetJogador.ok) {
        alert("Erro a encontrar jogador, Status: " + responseGetJogador.status)
    } else {
        const jogadorRetornado = await responseGetJogador.json();
        nomeAlterar.value = jogadorRetornado.nome;
        numeroCamisa.value = Number(jogadorRetornado.numeroCamisa);

        const responseGetTimes = await fetch("/times");
        if (!responseGetTimes.ok) {
            alert("Erro ao encontrar os times, Status: " + responseGetTimes.status)
        } else {
            const times = await responseGetTimes.json();
            timesList.innerHTML = "<option value=\"\">Selecione</option>";
            for (const time of times) {
                const opt = document.createElement("option");
                opt.value = time.id;
                opt.textContent = time.nome;
                timesList.append(opt);
                if (jogadorRetornado.time.id === time.id) {
                    opt.selected = true;
                }
            }
        }
        radiosLesionado.forEach(radio => {
            if (radio.value === String(jogadorRetornado.lesionado)) {
                radio.checked = true;
            }
        });

        descricao.value = jogadorRetornado.descricao;
    }
}

// Função de salvar jogador alterado.
const formulario = document.getElementById("formAlterar");
formulario.addEventListener("submit", async function (evt) {
    evt.preventDefault();
    const dados = new FormData(formulario);
    let timeData, numeroCamisaData;
    if (dados.get("time") === "") {
        timeData = null;
    } else {
        timeData = Number(dados.get("time"));
    }
    if (dados.get("numeroCamisa") === "") {
        numeroCamisaData = null;
    } else {
        numeroCamisaData = Number(dados.get("numeroCamisa"));
    }
    const jogador = {
        nome: dados.get("nome"),
        numeroCamisa: numeroCamisaData,
        idTime: timeData,
        lesionado: dados.get("lesionado") === "true",
        descricao: dados.get("descricao")
    }

    const response = await fetch(
        `/jogadores/${jogadorAlterado}`, {
            method: "PUT",
            body: JSON.stringify(jogador),
            headers: {
                "Content-Type": "application/json"
            }
        }
    );
    if (response.status === 400) {
        const erros = await response.json();
        erroNome.innerText = "";
        erroCamisa.innerText = "";
        erroLesionado.innerText = "";
        erroTime.innerText = "";
        for (const [chave, valor] of Object.entries(erros)) {
            if (chave === "nome") {
                erroNome.innerText = valor;
            } else if (chave === "numeroCamisa") {
                erroCamisa.innerText = valor;
            } else if (chave === "lesionado") {
                erroLesionado.innerText = valor;
            } else if (chave === "idTime") {
                erroTime.innerText = valor;
            }
        }
    } else if (!response.ok) {
        alert("Erro ao alterar o jogador, Status: " + response.status);
    } else {
        alert("Jogador alterado com sucesso!");
        window.location.href = "index.html";
    }
})

// Função para excluir o jogador
async function deleteJogador() {
    const idJogador = this.dataset.id;

    const confirmado = window.confirm("Você realmente deseja excluir o jogador ID " + idJogador + "?");

    if (confirmado) {
        const response = await fetch(`/jogadores/${idJogador}`, {
            method: "DELETE"
        });
        if (!response.ok) {
            alert("Não foi possível excluir o jogador, Status: " + response.status);
        } else {
            alert("Jogador excluído com sucesso!");
            window.location.href = "index.html";
        }
    }
}