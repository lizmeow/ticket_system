const updateTicketForm = document.querySelector("#contact-form-update-ticket");

updateTicketForm.onsubmit = async function(evt) {
  evt.preventDefault();
  // consume the fields from the form
  const ticketId = document.querySelector("#ticketId").value;
  const state = document.querySelector("#state").value;
  const resolutionText = document.querySelector("#resolutionText").value;
  const resolvedByUserId = document.querySelector("#resolvedByUserId").value;
  console.log(ticketId);
  console.log(state);
  console.log(resolutionText);
  console.log(resolvedByUserId);

  const updateTicket = {
    "state": state,
    "resolutionText": resolutionText,
    "userId" : resolvedByUserId
  }
  // make PUT axios request
  url = `https://kerej7074m.execute-api.us-west-2.amazonaws.com/prod4/tickets/${ticketId}`;
  axios.put(url, updateTicket)
  .then(function (response) {
    // handle success
    console.log(response);
    if (response.data.ticket != null) {
        // go to the ticket page for the updated ticket with the new ticket id
        window.location.href = `ticket.html?id=${response.data.ticket.ticketId}`;
    }
    else {
        populateErrorMessage(response.data.errorMessage);
    }
  })
 }

 function populateErrorMessage(error) {
     const errorDiv = document.createElement("div");
     const errorH2 = document.createElement("h2");
     const errorText = document.createTextNode(error);
     errorH2.appendChild(errorText);
     errorH2.setAttribute("class", "header");
     errorDiv.appendChild(errorH2);
     updateTicketForm.appendChild(errorDiv);
 }