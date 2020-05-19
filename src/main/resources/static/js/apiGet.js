//
// const getTree = () => {
//
//     axios.get('http://localhost:8181/tree/getAll', configGet)
//         .then(function (response) {
//             let currentID = document.getElementById("characterfind").value;
//             document.querySelector('#charactername').innerHTML = response.data[currentID].name
//             document.querySelector('#strength').innerHTML = response.data[currentID].abilities[0].strength;
//             document.querySelector('#dexterity').innerHTML = response.data[currentID].abilities[0].dexterity;
//
//             let skilllist = response.data[currentID].skills[0].skillName;
//             for (let i = 0; i < response.data.length; i++) {
//
//             }
//             console.log(skilllist.toString());
//
//         })
//         .catch(function (error) {
//             console.log(error);
//         });
// }
//
// let refreshCharacter = document.querySelector('#refreshCharacter');
// refreshCharacter.addEventListener('click', getCharacterSheets);
//
// }