const formulario = document.getElementById("formInclusao");

formulario.addEventListener("submit", async function (evt) {
    evt.preventDefault();
    const dados = new FormData(formulario);
    const jogador = {
        nome: dados.get("nome"),
        numeroCamisa: dados.get("numeroCamisa"),
        time: dados.get("time"),
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
    if (!response.ok) {
    alert("Não foi possivel cadastrar esse jogador, Status: " + response.status);
    } else {
        alert("Jogador cadastrado com sucesso!");
        window.location.href = "index.html";
    }

})