const ticketWrapper = document.querySelector(".wrapper");
const urlParams = new URLSearchParams(window.location.search);
const createdByUserId = urlParams.get('id');
console.log('created by user id is ...');
console.log(createdByUserId);

window.onload = async function(evt) {
  evt.preventDefault();
if (createdByUserId == "") {
  populateErrorMessage("Must enter a user id.");
}
else {
  const url = `https://kerej7074m.execute-api.us-west-2.amazonaws.com/prod4/tickets/createdBy/users/${createdByUserId}`;
  axios.get(url)
    .then(function (response) {
      // handle success
      console.log(response);
      if (response.data.ticketModelList != null) {
        populateTicketLinks(response.data.ticketModelList);
       }
      else {
        populateErrorMessage(response.data.errorMessage);
      }
    })
  }
}

function populateTicketLinks(ticketData) {
    for (let ticket of ticketData) {
        let li = document.createElement("li");
        let a = document.createElement("a");
        let text = document.createTextNode(ticket.ticketId);

        a.setAttribute('href', `./ticket.html?id=${ticket.ticketId}`);
        a.appendChild(text);
        li.appendChild(a);
        ticketWrapper.appendChild(li);
    }

    // back link
    const backLink = document.createElement("a");
    const linkText = document.createTextNode("Home");
    backLink.appendChild(linkText);
    backLink.setAttribute("href", "index.html");
    ticketWrapper.appendChild(backLink);

}

  function populateErrorMessage(error) {
      const errorDiv = document.createElement("div");
      const errorH2 = document.createElement("h2");
      const errorText = document.createTextNode(error);
      errorH2.appendChild(errorText);
      errorH2.setAttribute("class", "header");
      errorDiv.appendChild(errorH2);
      ticketWrapper.appendChild(errorDiv);

      // back link
      const backLink = document.createElement("a");
      const linkText = document.createTextNode("Home");
      backLink.appendChild(linkText);
      backLink.setAttribute("href", "index.html");
      ticketWrapper.appendChild(backLink);
  }