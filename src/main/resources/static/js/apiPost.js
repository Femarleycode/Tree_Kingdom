
const postTree= () => {
    let treeName = document.getElementById("treeName").value;
    let orderName = document.getElementById("orderName").value;
    console.log(treeName);
    console.log(orderName);

    axios({
        method: 'post',
        url: 'http://localhost:8181/tree/create',
        data: `{
        "treeName": "${treeName}",
        "orderName": "${orderName}"
    }`,
        headers: {'Content-Type': 'application/json' }
    })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (response) {
                console.log(response.data);
        });

}

let postButton = document.querySelector('#postButton');
postButton.addEventListener('click', postTree);
