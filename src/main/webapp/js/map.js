window.addEventListener("load", initMap);
window.addEventListener("load", initIcons);

// Global Variable for the map
let myMap;

let redIcon;
let blueIcon;

let prevSelectedMarker = null;
let selectedPoiId = null;

// Initialisierung der Karte
function initMap() {
  myMap = L.map("mapid").setView([49.250723, 7.377122], 13);

  L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
    maxZoom: 21,
    attribution:
      '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
  }).addTo(myMap);

  getAllPois();

  markers = L.layerGroup().addTo(myMap);

  showMarker();
}

function initIcons() {
  redIcon = new L.Icon({
    iconUrl: "./icon/marker-icon-red.png",
    shadowUrl: "./icon/marker-shadow.png",
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41],
  });

  blueIcon = new L.Icon({
    iconUrl: "./icon/marker-icon-blue.png",
    shadowUrl: "./icon/marker-shadow.png",
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41],
  });
}

async function getPoiById(poiId) {
  const response = await fetch(
    window.endpointConfig.local.SERVICES_BASE_URL + `/pois/${poiId}`,
    {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    }
  );

  if (!response.ok) {
    throw new Error(`Http Error! status:  ${response.status}`);
  }
  const data = await response.json();
  return data;
}

async function getAllPois() {
  const response = await fetch(
    window.endpointConfig.local.SERVICES_BASE_URL + "/pois",
    {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    }
  );

  if (!response.ok) {
    const message = `An error has occured: ${response.status}`;
    throw new Error(message);
  }
  const pois = await response.json();
  return pois;
}
async function showMarker() {
  const allPois = await getAllPois();
  allPois.forEach((poi) => {
    L.marker([poi.position.lat, poi.position.lon], { icon: blueIcon })
      .addTo(myMap)
      .on("click", async (event) => {
        await onPoiSelected(poi, event);
      });
  });
}

async function onPoiSelected(poi, event) {
  if (prevSelectedMarker != null) {
    // unselect
    prevSelectedMarker.setIcon(blueIcon);
  }
  // set selected
  prevSelectedMarker = event.target;
  prevSelectedMarker.setIcon(redIcon);

  selectedPoiId = poi.osmId;
  getPoitags(poi.osmId);
}

function drawPoiInfo(poi) {
  let poiName = document.getElementById("poiName");
  for (const p of poi) {
    if (p.id.tag === "name") {
      poiName.innerHTML = p.value;
      break;
    }
  }
}

function getPoitags(id) {
  console.log(id);
  fetch(window.endpointConfig.local.SERVICES_BASE_URL + `/pois/${id}`, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
  })
    .then((response) => response.json())
    .then((poitag) => {
      console.log(poitag);
      for (const p of poitag) {
        if (p.tag === "name") {
          document.getElementById("poiinfo").innerHTML = p.value;
          break;
        }
      }
    })
    .catch((error) => console.error("Error:", error));
}
