// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
/**function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/** Fetches the current date from the server and adds it to the page. */
/**async function showFacts() {
  const responseFromServer = await fetch('/facts');
//   const textFromResponse = await responseFromServer.text();
  console.log(responseFromServer);

  const factsStrings = await responseFromServer.json();
  const randomFact = factsStrings[Math.floor(Math.random() * factsStrings.length)];

  console.log(randomFact);
  alert(randomFact);
}*/

function loadUsers() {
  fetch('/list-users').then(response => response.json()).then((users) => {
    const userListElement = document.getElementById('users-list');
    users.forEach((user) => {
      userListElement.appendChild(createUserElement(user));
    })
  });
}

/** Creates an element that represents a user */
function createUserElement(user) {
  const userElement = document.createElement('li');
  userElement.className = 'user';

  const nameElement = document.createElement('span');
  nameElement.innerText = user.name;    
  
  userElement.appendChild(nameElement);
  return userElement;
}