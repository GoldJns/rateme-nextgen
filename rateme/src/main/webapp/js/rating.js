window.addEventListener("load", init);

/* ==================================================== */

function init() {
  var starForm = document.getElementById("ratingForm");
  starForm.addEventListener("submit", handleRatingSubmit, false);
}

function formatTimestamp(date) {
  const timestamp = new Date(date);

  const formattedDate = timestamp
    .toLocaleString("de", {
      day: "2-digit",
      month: "2-digit",
      year: "2-digit",
    })
    .replace(/\//g, ".");
  return formattedDate;
}

async function handleRatingSubmit(event) {
  let errorMsg = "";
  event.preventDefault();
  var ratingFieldset = document.querySelector(".rating");
  var starInput = ratingFieldset.querySelector('input[name="rating"]:checked');
  var starValue = starInput ? starInput.value : null;

  if (!starValue) errorMsg = "No stars given";
  const ratingText = document.getElementById("ratingText").value.trim();
  if (!ratingText) {
    errorMsg = "Rating empty";
  }
  const loggedIn = sessionStorage.getItem("accessToken");
  if (!loggedIn) {
    errorMsg = "Login required";
  }

  if (errorMsg) {
    showSnackbar(errorMsg, "error");
    return;
  }

  try {
    let file = document.getElementById("imageinput").files[0];
    var reader = new FileReader();

    const postActions = new Promise((resolve, reject) => {
      try {
        if (file) {
          reader.readAsDataURL(file);
          reader.onload = function () {
            let data = {
              stars: starValue,
              text: ratingText,
              osmId: selectedPoiId,
              imageBlob: reader.result,
            };
            postRating(data).then(() => {
              drawPoiRating(selectedPoiId);
              resolve(true);
            });
          };
          reader.onerror = function (error) {
            console.log("Error: ", error);
            reject(error);
          };
        } else {
          let data = {
            stars: starValue,
            text: ratingText,
            osmId: selectedPoiId,
            imageBlob: "",
          };
          postRating(data).then(() => {
            drawPoiRating(selectedPoiId);
            resolve(true);
          });
        }
      } catch (error) {
        reject(error);
      }
    });

    await postActions;
    drawTable();
  } catch (error) {
    console.log(error);
  }
}

function addUserRating(rating) {
  var table = document.getElementById("myRatingsTable");
  if (!rating) return;

  var rowCount = table.rows.length;
  let formattedDate;

  let poiName;
  for (const p of ratedPoi.tags) {
    if (p.id.tag === "name") {
      poiName = p.value;
      break;
    }
  }

  formattedDate = formatTimestamp(rating.createdAt);

  var row = table.insertRow(rowCount);
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);
  var cell4 = row.insertCell(3);
  var cell5 = row.insertCell(4);
  cell5.style.width = "15%";
  cell4.style.width = "15%";
  cell3.innerHTML = rating.text;
  cell2.innerHTML = formattedDate;
  cell2.style.width = "10%";
  cell1.innerHTML = poiName;
  cell1.style.width = "20%";

  cell4.innerHTML = drawRatingStars(rating.stars);
  cell5.innerHTMK = `<image src=${rating.imageBlob} width="60", height="50" alt="rating image"/>`;
}

function drawRatingStars(stars) {
  let starRatings = "<div>";
  for (let i = 0; i < 5; i++) {
    if (i < stars) {
      starRatings += `<span class="fa fa-star checked"></span>`;
    } else {
      starRatings += `<span class="fa fa-star"></span>`;
    }
  }
  starRatings += "</div>";
  return starRatings;
}

async function fetchUserRatingsEntries() {
  const ratingsByUser = await getAllRatingsByUser();
  if (!ratingsByUser || ratingsByUser.length == 0) {
    return null;
  }
  return Array.from(ratingsByUser);
}

async function displayUserRatingRow(rating) {
  let table = document.getElementById("myRatingsTable");
  const formattedDate = formatTimestamp(rating.createdAt);
  let poiName;

  const ratedPoi = await getPoiById(rating.osmId);

  for (p of ratedPoi.tags) {
    if (p.id.tag === "name") {
      poiName = p.value;
      break;
    }
  }

  let rowCount = table.rows.length;
  let starRatings = "<div>";
  let row = table.insertRow(rowCount);
  let cell1 = row.insertCell(0);
  let cell2 = row.insertCell(1);
  let cell3 = row.insertCell(2);
  let cell4 = row.insertCell(3);
  let cell5 = row.insertCell(4);
  cell5.style.width = "15%";
  cell4.innerHTML = rating.stars;
  cell4.style.width = "15%";
  cell3.innerHTML = rating.text;
  cell2.innerHTML = formattedDate;
  cell2.style.width = "10%";
  cell1.innerHTML = poiName;
  cell1.style.width = "20%";

  for (let i = 0; i < 5; i++) {
    if (i < rating.stars) {
      starRatings += `<span class="fa fa-star checked"></span>`;
    } else {
      starRatings += `<span class="fa fa-star"></span>`;
    }
  }
  starRatings += "</div>";
  cell4.innerHTML = starRatings;
  cell5.innerHTML = `<image src=${rating.imageBlob} width="60", height="50" alt="rating image"/>`;
}

async function getAllRatingsByUser() {
  try {
    const accessToken = sessionStorage.getItem("accessToken");
    const response = await fetch(serviceUrl + "/ratings/user", {
      method: "GET",
      headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "*",
        token: accessToken,
      },
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.log(error);
  }
}

async function drawPoiRating(poiId) {
  const ratingContainer = document.getElementById("poiRatingsSection");
  const ratingTitle = document.getElementById("poiRatingsTitle");
  ratingContainer.style.display = "none";
  ratingTitle.style.display = "none";
  const ratingsByPoi = await getRatingsByPoi(poiId);
  if (!ratingsByPoi || !ratingsByPoi.length) return;

  while (ratingContainer.firstChild) {
    ratingContainer.removeChild(ratingContainer.firstChild);
  }

  for (const rating of ratingsByPoi) {
    const timestamp = formatTimestamp(rating.createdAt);
    const ratingUser = await getUserById(rating.userId);

    const ratingEntry = document.createElement("div");
    ratingEntry.id = "poiRatingsSectionEntry";

    const ratingStars = document.createElement("span");
    ratingStars.innerHTML = drawRatingStars(rating.stars);

    const userRatingTimestamp = document.createElement("span");
    userRatingTimestamp.id = "userRatingTimestamp";
    userRatingTimestamp.textContent = ratingUser
      ? `${ratingUser.userName} writes on ${timestamp}:`
      : "";

    const userRatingText = document.createElement("span");
    userRatingText.id = "userRatingText";
    userRatingText.textContent = rating.text;

    ratingEntry.appendChild(ratingStars);
    ratingEntry.appendChild(userRatingTimestamp);
    ratingEntry.appendChild(userRatingText);

    if (rating.imageBlob) {
      const ratingImage = document.createElement("img");
      ratingImage.src = rating.imageBlob;
      ratingImage.width = "60";
      ratingImage.height = "50";
      ratingImage.alt = "rating image";
      ratingEntry.appendChild(ratingImage);
    }

    const hrElement = document.createElement("hr");
    ratingEntry.appendChild(hrElement);

    ratingContainer.appendChild(ratingEntry);
  }

  ratingContainer.style.display = "block";
  ratingTitle.style.display = "block";
}

async function getRatingsByPoi(poiId) {
  try {
    const response = await fetch(serviceUrl + `/ratings/poi/${poiId}`, {
      method: "GET",
      headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    });
    if (!response.ok) {
      throw new Error("Failed to get Poi");
    }
    const ratingsByPoi = await response.json();

    return ratingsByPoi;
  } catch (error) {
    console.log(error);
  }
}

function showSnackbar(message, severity) {
  let snackbar = document.getElementById("snackbar");
  let snackbarMsg = document.getElementById("snackbarMsg");
  snackbarMsg.innerHTML = message;
  if (severity === "error") snackbar.style.backgroundColor = "#ba0719";
  else snackbar.style.backgroundColor = "#5fc22d";
  snackbar.className = "show";
  setTimeout(() => {
    snackbar.className = snackbar.className.replace("show", "");
  }, 3000);
}

async function postRating(rating) {
  try {
    const accessToken = sessionStorage.getItem("accessToken");
    await fetch(serviceUrl + "/ratings", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        "Access-Control-Allow-Origin": "*",
        token: accessToken,
      },
      body: JSON.stringify(rating),
    });

    showSnackbar("Created rating", "success");
  } catch (error) {
    console.error(error);
  }
}
