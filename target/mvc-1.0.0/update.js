//function to populate update form when UPDATE btn is clicked
function  updateFunction1(animal_id){
    // e.preventDefault();// stope executing a page refresh
     // post request shoul be sent here:
     let url = "/mvc/animalapi?id="+ animal_id;
 
     // promise represents the result of an asynchronious operation
     let promise = axios.get(url);
     //Callback for a successful response
     promise.then(resp => {
         console.log("Promise Response: " + JSON.stringify(resp.data));
         //add to the table
         setDetails(resp.data);
 
     });
     //Callback for a failed response
     promise.catch(function (response) {
         console.log("Promise Failed: " + response);
     });
 
     // axios.post(url, animal).then(function(){}).catch(function(){});
 };
 
 // when the form with id="update" is submitted
document.getElementById("update").addEventListener('submit', function(e){
    e.preventDefault();// stope executing a page refresh

    // get values from the update form
    let updateFormAnimal = {
        animal_id: document.getElementById('updateId').value,
        name: document.getElementById('updateName').value,
        color: document.getElementById('updateColor').value
    };

    // put request should be sent here:
    let url = "/mvc/animalapi";

    // promise represents the result of an asynchronious operation
    let promise = axios.put(url, updateFormAnimal);
    //Callback for a successful response
    promise.then(resp => {
        console.log("Promise Response: " + JSON.stringify(resp.data));
        // clear the form fields
        document.getElementById('updateId').value = null;
        document.getElementById('updateName').value = null;
        document.getElementById('updateColor').value = null;
    })
    // once the first response is recieved, refresh animals table
    .then( resp2 => {
        document.getElementById("list").innerHTML = "";
        // we have this function in script.js
        getAllAnimals();
    }
    )
    ;
    //Callback for a failed response
    promise.catch(function (response) {
        console.log("Promise Failed: " + response);
    });

    // axios.post(url, animal).then(function(){}).catch(function(){});
});

 // Set input values to the update form
 function setDetails(animal){
    document.getElementById("updateId").value = animal.animal_id;
    document.getElementById("updateName").value = animal.name;
    document.getElementById("updateColor").value = animal.color;
 }