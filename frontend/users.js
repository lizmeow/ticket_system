const userList = document.querySelector("#users");

window.onload = async function(evt) {
  evt.preventDefault();
  axios.get('https://kerej7074m.execute-api.us-west-2.amazonaws.com/prod4/users')
    .then(function (response) {
      // handle success
      console.log(response);
      populateUserlists(response.data.userList);
    })
}

function populateUserlists(contactData) {

  for (let contact of contactData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let id = contact.id;
    let name = contact.name;
    let text = document.createTextNode(id.concat(" ", name));
    a.appendChild(text);
    li.appendChild(a);
    userList.appendChild(li);
  }
  const contactDiv = document.createElement("div");
  const linkText = document.createTextNode("Home");
  const backLink = document.createElement("a");
  backLink.appendChild(linkText);
  backLink.setAttribute("href", "index.html");
  contactDiv.appendChild(backLink);
  userList.appendChild(contactDiv);
}

