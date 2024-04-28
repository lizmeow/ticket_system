const ticketIdForm = document.querySelector("#contact-form-ticket-id");
const createdByUserId = document.querySelector("#contact-form-created-by-user-id");
const assignedToUserId = document.querySelector("#contact-form-assigned-to-user-id");

assignedToUserId.onsubmit = async function(evt) {
  evt.preventDefault();
  const assignedToUserId = document.querySelector("#assignedToUserId").value;
  console.log(assignedToUserId);
  window.location.href = `ticketsAssignedTo.html?id=${assignedToUserId}`;
 }

createdByUserId.onsubmit = async function(evt) {
  evt.preventDefault();
  const createdByUserId = document.querySelector("#createdByUserId").value;
  console.log(createdByUserId);
  window.location.href = `ticketsCreatedBy.html?id=${createdByUserId}`;
 }

ticketIdForm.onsubmit = async function(evt) {
  evt.preventDefault();
  const ticketId = document.querySelector("#ticketId").value;
  console.log(ticketId);
  window.location.href = `ticket.html?id=${ticketId}`;
//  axios.get('https://kerej7074m.execute-api.us-west-2.amazonaws.com/prod3/tickets/', ticketId)
//    .then(function (response) {
//      // handle success
//      console.log(response);
//      populateUserlists(response.data.userList);
//    })
}

//window.onload = async function(evt) {
//  evt.preventDefault();
//  console.log("Getting Playlist Data...");
//  axios.get("https://svebsuap66.execute-api.us-west-2.amazonaws.com/prod/playlists", {
//    authorization: {
//      'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
//    }
//  }).then((res) => {
//    console.log(res.data);
//    populatePlaylists(res.data.playlists);
//  })
//}
//
//function populatePlaylists(playlistData) {
//
//  for (let playlist of playlistData) {
//    let li = document.createElement("li");
//    let a = document.createElement("a");
//    let text = document.createTextNode(playlist.name);
//
//    a.setAttribute('href', `./playlist.html?id=${playlist.id}`);
//
//    a.appendChild(text);
//    li.appendChild(a);
//    playlistsList.appendChild(li);
//  }
//}
