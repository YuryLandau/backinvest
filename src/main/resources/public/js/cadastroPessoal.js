
// DB

function leDados (){
  let strDados = localStorage.getItem('db');
  let objDados = {};

  if (strDados) {
    objDados = JSON.parse (strDados);
  }
  else {
    objDados = { cadastros: [
      {nome: "Alexandre", sobrenome: "Lacerda",    email: "xand.lacerda.2018@gmail.com", senha: "09932986430" },

                             ]}
  }

  return objDados;
}

function salvaDados (dados) {
  localStorage.setItem ('db', JSON.stringify (dados));
}

function incluirCadastro (e){
  e.preventDefault();
  // Ler dados do localStorage
  let objDados = leDados();

  // Incluir um novo Cadastro
  let strName = document.getElementById ('name').value;
  let strLastname = document.getElementById ('lastname').value;
  let strEmail = document.getElementById('email').value;
  let strPassword = document.getElementById('password').value;
  let novoCadastro = {
      nome: strName,
      sobrenome: strLastname,
      email: strEmail,
      senha: strPassword
  };
  objDados.cadastros.push (novoCadastro);


  fetch("http://localhost:4567/api/users/registration/", {
  method: "POST",
  body: JSON.stringify({
    password:strPassword,
  email: strEmail,
  firstname: strName,
  lastname: strLastname,
  cpf: "cpf",
  accept: true
}),
  headers: {
    "Content-type": "application/json; charset=UTF-8"
  }
})
}

function imprimeDados () {
  let tela = document.getElementById('tela');
  let strHtml = '';
  let objDados = leDados ();

  for (i = 0; i < objDados.cadastros.length; i++) {
    strHtml += `<p>${objDados.cadastros[i].nome} - ${objDados.cadastros[i].sobrenome} - ${objDados.cadastros[i].email} - ${objDados.cadastros[i].senha}</p>`
  }

  tela.innerHTML = strHtml;
}

// Config Botões
document.getElementById ('btnCarregaDados').addEventListener ('click', imprimeDados);
document.getElementById ('btn-submit').addEventListener ('click', incluirCadastro);