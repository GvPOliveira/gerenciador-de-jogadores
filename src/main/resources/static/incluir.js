const formulario = document.getElementById("formInclusao");
getTimes();
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
        lesionado: dados.get("lesionado") === "sim",
        descricao: dados.get("observacoes")
    }

    const response = await fetch(
        "/jogadores", {
            method: "POST",
            body: JSON.stringify(jogador),
            headers: {
                "Content-Type": "application/json"
            }
        }
    );
    if (response.status === 400) {
        const erros = await response.json();
        const erroNome = document.getElementById("erroNome");
        const erroCamisa = document.getElementById("erroNumeroCamisa");
        const erroLesionado = document.getElementById("erroLesionado");
        const erroTime = document.getElementById("erroTime");
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
        alert("Não foi possivel cadastrar esse jogador, Status: " + response.status);
    } else {
        alert("Jogador cadastrado com sucesso!");
        window.location.href = "index.html";
    }
})

// Função para buscar times e sobrescrever o select de times

async function getTimes() {
    const response = await fetch("/times");
    if (!response.ok) {
        alert("Não foi possivel carregar os times, Status: " + response.status);
    } else {
        const times = await response.json();
        if (times.length > 0) {
            for (const time of times) {
                const timesList = document.getElementById("times");
                const opt = document.createElement("option");
                opt.value = time.id;
                opt.textContent = time.nome;
                timesList.append(opt);
            }
        } else {
            alert("Nenhum time cadastrado, cadastre um time na tela inicial.")
        }
    }
}
