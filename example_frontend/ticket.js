const ticket = document.querySelector(".wrapper");
const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

window.onload = async function(evt) {
  evt.preventDefault();
  if (id == "") {
    populateErrorMessage("Must enter a ticket id");
  }
  else {
    const url = `https://kerej7074m.execute-api.us-west-2.amazonaws.com/prod4/tickets/${id}`;
    axios.get(url)
      .then(function (response) {
        // handle success
        console.log(response);
        if (response.data.ticket != null) {
          // no ticket id was found
          populateTicket(response.data.ticket);
        }
        else {
          // ticket id was found
          populateErrorMessage(response.data.errorMessage);
        }
      })
    }
}

function populateErrorMessage(error) {
    const errorDiv = document.createElement("div");
    const errorH2 = document.createElement("h2");
    const errorText = document.createTextNode(error);
    errorH2.appendChild(errorText);
    errorH2.setAttribute("class", "header");
    errorDiv.appendChild(errorH2);
    ticket.appendChild(errorDiv);

    const backLink = document.createElement("a");
    const linkText = document.createTextNode("Home");
    backLink.appendChild(linkText);
    backLink.setAttribute("href", "index.html");
    errorDiv.appendChild(backLink);
    ticket.appendChild(errorDiv);

}

function populateTicket(ticketData) {
    const ticketDiv = document.createElement("div");
    const ticketH2 = document.createElement("h2");
    const shortP = document.createElement("p");
    const longP = document.createElement("p");
    const creationP = document.createElement("p")
    const createdByP = document.createElement("p")
    const assignedToP = document.createElement("p")
    const stateP = document.createElement("p")
    const resolutionP = document.createElement("p")

    const ticketIdText = document.createTextNode(ticketData.ticketId);
    const shortDescriptionText = document.createTextNode(ticketData.shortDescription);
    const longDescriptionText = document.createTextNode(ticketData.longDescription);
    const creationDateText = document.createTextNode(ticketData.creationDate);
    const createdByText = document.createTextNode(ticketData.createdByUserId);
    const assignedToText = document.createTextNode(ticketData.assignedToUserId);
    const stateText = document.createTextNode(ticketData.state);
    const resolutionText = document.createTextNode(ticketData.resolutionText);

    ticketH2.appendChild(ticketIdText);
    ticketH2.setAttribute("class", "header");

    shortP.appendChild(shortDescriptionText)
    shortP.setAttribute("class", "description");

    longP.appendChild(longDescriptionText)
    longP.setAttribute("class", "description");

    creationP.appendChild(creationDateText)
    creationP.setAttribute("class", "description");

    createdByP.appendChild(createdByText)
    createdByP.setAttribute("class", "description");

    assignedToP.appendChild(assignedToText)
    assignedToP.setAttribute("class", "description");

    stateP.appendChild(stateText)
    stateP.setAttribute("class", "description");

    resolutionP.appendChild(resolutionText)
    resolutionP.setAttribute("class", "description");

    ticketDiv.appendChild(ticketH2);
    ticketDiv.appendChild(shortP);
    ticketDiv.appendChild(longP);
    ticketDiv.appendChild(creationP);
    ticketDiv.appendChild(createdByP);
    ticketDiv.appendChild(assignedToP);
    ticketDiv.appendChild(stateP);
    ticketDiv.appendChild(resolutionP);

    const backLink = document.createElement("a");
    const linkText = document.createTextNode("Home");
    backLink.appendChild(linkText);
    backLink.setAttribute("href", "index.html");
    ticketDiv.appendChild(backLink);
    ticket.appendChild(ticketDiv);
}
