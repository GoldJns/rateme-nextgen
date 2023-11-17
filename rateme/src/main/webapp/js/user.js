window.addEventListener("load", initUser);
window.addEventListener("beforeunload", checkLoginStatus);

let user;


async function checkLoginStatus() {
  const token = sessionStorage.getItem("accessToken");

  if (token) {
    try {
      const userResponse = await fetchUser(token);
      user = userResponse;
      if (user.userName) {
        showLogoutView();
      } else {
        showLoginView();
      }
    } catch (error) {
      console.error("Error fetching user:", error);
    }
  }
}

function initUser() {
  checkLoginStatus();
  var loginBtn = document.getElementById("loginBtn");
  var logoutBtn = document.getElementById("logoutBtn");
  var registrationBtn = document.getElementById("registrationOpen");
  var registrationForm = document.getElementById("registrationForm");
  var input = document.getElementById("inputPasswort");
  var eyeIcon = document.getElementById("togglePassword");
  var cancelBtn = document.getElementById("registrationClose");

  // EVENT-LISTENER BINDINGS
  eyeIcon.addEventListener("click", handlePasswordToggle, false);
  input.addEventListener("keyup", handlePasswordValidation, false);
  cancelBtn.addEventListener("click", handleRegistrationClose, false);
  registrationForm.addEventListener("submit", handleRegistrationSubmit, false);
  registrationBtn.addEventListener("click", handleRegistrationSubmit, false);
  logoutBtn.addEventListener("click", handleLogout, false);
  loginBtn.addEventListener("click", handleLogin, false);
}

function validateRegistration() {
  const nameRegex = /^[A-Z][a-zA-Z]*[a-z]$/;
  const validationMap = new Map([
    ["inputVorname", { regex: nameRegex, elementErrorId: "vornameError" }],
    [
      "inputNachname",
      {
        regex: nameRegex,
        elementErrorId: "nachnameError",
      },
    ],
    ["inputStrasse", { regex: true, elementErrorId: "adresseError" }],
    ["inputNr", { regex: /^[0-9]*$/, elementErrorId: "adresseError" }],
    ["inputPlz", { regex: /\b\d{5}\b/, elementErrorId: "plzOrtError" }],
    ["inputOrt", { regex: true, elementErrorId: "plzOrtError" }],
    [
      "inputEmail",
      {
        regex: /^[a-z]{4}\d{4}\@stud.hs-kl.de$/,
        elementErrorId: "emailError",
      },
    ],
    ["inputBenutzerId", { regex: true, elementErrorId: "benutzerIdError" }],
    ["inputPasswort", { regex: true, elementErrorId: "passwortError" }],
  ]);

  let valid = true;
  for (const [userInput, { regex, elementErrorId }] of validationMap) {
    const inputElement = document.getElementById(userInput);
    const label = document.querySelector(
      'label[for="' + userInput + '"]'
    ).innerHTML;
    const inputValue = inputElement.value;
    let errorDiv = document.getElementById(elementErrorId);
    errorDiv.style.display = "none";
    errorDiv.innerHTML = `Format for ${label} wrong`;
    const empty = !inputValue.trim().length;
    if (empty) {
      errorDiv && (errorDiv.innerHTML = "Please specify");
      errorDiv && (errorDiv.style.display = "block");
      valid = false;
      continue;
    }
    const validFormat = regex === true ? true : regex.test(inputValue);
    if (!validFormat) {
      errorDiv && (errorDiv.style.display = "block");
      valid = false;
    }
  }
  return valid;
}

function validatePassword(password) {
  const passwordLength = password.trim().length;
  if (!passwordLength) return 0;
  let passwordStrength = 1;
  if (passwordLength > 5) {
    passwordStrength++; //ok
    const containsUpper = /[A-Z]/.test(password);
    const containsLower = /[a-z]/.test(password);
    const lowerAndUpper = containsUpper && containsLower;
    if (lowerAndUpper) {
      passwordStrength++; // mittel sicher
      const containsDigits = /\d/.test(password);
      const containsSpecialChars = /[§,$,%,&,!,?.]/.test(password);
      const strong = containsDigits && containsSpecialChars;
      if (strong) {
        passwordStrength++;
        if (passwordLength > 7) {
          passwordStrength++;
        }
      }
    }
  }
  return passwordStrength;
}

function drawCanvas(pwdStrength) {
  let canvas = document.getElementById("pwdCanvas");
  var ctx = canvas.getContext("2d");
  ctx.fillStyle = "#FFFFFF";
  ctx.fillRect(0, 0, canvas.width, canvas.height);
  var grd = ctx.createLinearGradient(0, 0, canvas.width, canvas.height);
  grd.addColorStop(1, "#00FF7F");
  grd.addColorStop(0.8, "#3BCA6D");
  grd.addColorStop(0.6, "#77945C");
  grd.addColorStop(0.4, "#B2574A");
  grd.addColorStop(0.2, "#ED2938");
  //https://www.csestack.org/hide-show-password-eye-icon-html-javascript/
  ctx.fillStyle = grd;
  ctx.fillRect(0, 0, (canvas.width / 5) * pwdStrength, canvas.height);
}

async function getUserById(userId) {
  try {
    const response = await fetch(`http://localhost:8080/user/${userId}`, {
      method: "GET",
      headers: { "Content-type": "application/json" },
    });
    const user = await response.json();
    return user;
  } catch (error) {
    console.error(error);
    showSnackbar("Failed to fetch user", "error");
  }
}

function handleRegistrationOpen() {
  document.getElementById("registration").style.display = "block";
}

function handleRegistrationClose() {
  document.getElementById("registration").style.display = "none";
}

function handlePasswordValidation() {
  var password = this.value;
  const strength = validatePassword(password);
  drawCanvas(strength);
}

function handlePasswordToggle() {
  const pwdInput = document.getElementById("inputPasswort");
  const pwdType =
    pwdInput.getAttribute("type") === "password" ? "text" : "password";
  pwdInput.setAttribute("type", pwdType);
  this.classList.toggle("fa-eye-slash");
}

async function fetchUser(token) {
  try {
    const response = await fetch("http://localhost:8080/user/", {
      method: "GET",
      headers: {
        "Content-type": "application/json",
        token: token,
      },
    });

    const responseUser = await response.json();
    user = responseUser;
    return user;
  } catch (error) {
    showSnackbar(error, "error");
  }
}

async function loginUser(credentials) {
  try {
    const response = await fetch("http://localhost:8080/access/login", {
      method: "POST",
      headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify(credentials),
    });
    const data = await response.json();

    if (data) {
      sessionStorage.setItem("accessToken", data.token);
    }

    checkLoginStatus();

    showSnackbar("Logged in", "success");
  } catch (error) {
    console.error(error);
    showSnackbar("Login failed", "error");
  }
}

async function handleLogin() {
  const userNameElement = document.getElementById("userNameLogin");
  const passwordElement = document.getElementById("passwordLogin");

  const userName = userNameElement.value.trim();
  const password = passwordElement.value.trim();

  if (!(userName && password)) {
    showSnackbar("Please fill out every field", "error");
    return;
  }

  const credentials = { userName, password };
  await loginUser(credentials);
}

async function handleRegistrationSubmit(event) {
  event.preventDefault();
  const valid = validateRegistration();
  if (!valid) return;
  handleRegistrationClose();

  const myForm = document.getElementById("registrationForm");
  const formData = new FormData(myForm);
  var registerData = {};
  formData.forEach(function (value, key) {
    registerData[key] = value;
  });
  var json = JSON.stringify(registerData);

  try {
    const response = await fetch("http://localhost:8080/user/register", {
      method: "POST",
      headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: json,
    });
    if (!response.ok) {
      showSnackbar("Failed to create user", "error");
      throw new Error(
        `Status: ${response.status}, Message: ${response.statusText}`
      );
    }

    const data = await response.json();
    sessionStorage.setItem("accessToken", data.token);
    checkLoginStatus();
  } catch (error) {
    showSnackbar(error, "error");
  }

  showSnackbar("User created successfully", "success");
}

function showLoginView() {
  const loginHeader = document.getElementById("loginHeader");
  const logoutHeader = document.getElementById("logoutHeader");

  const userNameInput = document.getElementById("userNameLogin");
  const passwordInput = document.getElementById("passwordLogin");
  const myRatingsSection = document.getElementById("userRatings");
  const myRatingsTable = document.getElementById("myRatingsTable");

  userNameInput.value = "";
  passwordInput.value = "";

  logoutHeader.style.display = "none";
  loginHeader.style.display = "flex";

  myRatingsSection.style.display = "none";
  myRatingsTable.innerHTML = "";
}

async function showLogoutView() {
  const loginInfo = document.getElementById("loginHeader");
  const logoutInfo = document.getElementById("logoutHeader");
  const welcomeLabel = document.getElementById("welcome");

  loginInfo.style.display = "none";
  logoutInfo.style.display = "block";
  welcomeLabel.innerHTML = `Willkommen, ${user.userName}`;
  await drawTable();
}

async function drawTable() {
  const myRatingsSection = document.getElementById("userRatings");
  const table = document.getElementById("myRatingsTable");
  table.innerHTML = "";
  myRatingsSection.style.display = "none";
  const userRatingsEntries = await fetchUserRatingsEntries();
  if (userRatingsEntries) {
    myRatingsSection.style.display = "block";
    for (const rating of userRatingsEntries) {
      await displayUserRatingRow(rating);
    }
  }
}

function handleLogout() {
  const id = sessionStorage.getItem("accessToken");
  fetch("http://localhost:8080/access/logout", {
    method: "delete",
    headers: { token: id },
  })
    .then((response) => {
      if (response.ok) {
        sessionStorage.removeItem("accessToken");
        showSnackbar("Logged out", "error");
        showLoginView();
      }
    })
    .catch((error) => console.error("Error:", error));
}
