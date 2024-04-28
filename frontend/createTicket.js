const createTicketForm = document.querySelector("#contact-form-create-ticket");

createTicketForm.onsubmit = async function(evt) {
  evt.preventDefault();
  // consume the fields from the form
  const createdByUserId = document.querySelector("#createdByUserId").value;
  const shortDescription = document.querySelector("#shortDescription").value;
  const longDescription = document.querySelector("#longDescription").value;
  const assignedToUserId = document.querySelector("#assignedToUserId").value;
  console.log(createdByUserId);
  console.log(shortDescription);
  console.log(longDescription);
  console.log(assignedToUserId);

  const newTicket = {
    "createdByUserId": createdByUserId,
    "shortDescription": shortDescription,
    "longDescription": longDescription,
    "assignedToUserId" : assignedToUserId
  }
  if (assignedToUserId === "") {
    populateErrorMessage("The user you assigned this ticket to is invalid");
  }
  else if (createdByUserId == "") {
    populateErrorMessage("The user trying to make the ticket is invalid.")
  }
  else {
      // make POST axios request
      url = `https://kerej7074m.execute-api.us-west-2.amazonaws.com/prod4/tickets/users/${createdByUserId}`;
      axios.post(url, newTicket)
      .then(function (response) {
        //handle success
        console.log(response);
        if (response.data.ticket != null) {
            // go to the ticket page for the newly created ticket with the new ticket id
            window.location.href = `ticket.html?id=${response.data.ticket.ticketId}`;
        }
        else {
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
      createTicketForm.appendChild(errorDiv);
  }