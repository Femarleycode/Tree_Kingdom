

const postTree= () => {
    let treeId = document.getElementById("treeId").value;
    let treeName = document.getElementById("treeName").value;
    let orderName = document.getElementById("orderName").value;

    axios({
        method: 'post',
        url: 'http://localhost:8181/createTree',
        data: `{
        "treeId": ${treeId},
        "treeName": ${treeName},
        "orderName": ${orderName},
    }`,
        headers: {'Content-Type': 'application/json' }
    })
        .then(function (response) {
            let newId = response.data.abilitiesId;
            console.log(newId);
        })
        .catch(function (response) {
                console.log(response.data);
        });

}

let postButton = document.querySelector('#postButton');
postButton.addEventListener('click', postTree);
