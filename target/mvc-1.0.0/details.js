function  detailsFunction(animal_id){
   // e.preventDefault();// stope executing a page refresh


    // post request shoul be sent here:
    let url = "/mvc/animalapi?id="+ animal_id;

    // promise represents the result of an asynchronious operation
    let promise = axios.get(url);
    //Callback for a successful response
    promise.then(resp => {
        console.log("Promise Response: " + JSON.stringify(resp.data));
        //add to the table
        appendDetails(resp.data);

    });
    //Callback for a failed response
    promise.catch(function (response) {
        console.log("Promise Failed: " + response);
    });

    // axios.post(url, animal).then(function(){}).catch(function(){});
};



function appendDetails(animal){
    console.log("add details");
document.getElementById("details").innerText= animal.animal_id + " " + animal.name + " " +  animal.color;

}