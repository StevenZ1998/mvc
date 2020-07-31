// when the form with id="add" is submitted
document.getElementById("add").addEventListener('submit', function(e){
    e.preventDefault();// stope executing a page refresh

    let formAnimal = {
        name: document.getElementById('name').value,
        color: document.getElementById('color').value
    };

    // post request shoul be sent here:
    let url = "/mvc/animalapi";

    // promise represents the result of an asynchronious operation
    let promise = axios.post(url, formAnimal);
    //Callback for a successful response
    promise.then(resp => {
        console.log("Promise Response: " + JSON.stringify(resp.data));
        //add to the table
        appendAnimal(resp.data);
        // clear the form fields
        document.getElementById('name').value = null;
        document.getElementById('color').value = null;
    });
    //Callback for a failed response
    promise.catch(function (response) {
        console.log("Promise Failed: " + response);
    });

    // axios.post(url, animal).then(function(){}).catch(function(){});
});


/*********************************************************************************************************************/
//This functions is called when DOMContent is loaded
document.addEventListener('DOMContentLoaded', getAllAnimals());

// Gets all animals from the backend when called
function getAllAnimals(){
        // AJAX CALL : XMLHttpRequest: retrieve data from a server after page is loaded 
        // in the background
        var xhr = new XMLHttpRequest();
        // ready state 0 - unopened
        xhr.open('GET', "/mvc/animalapi");
        
        // ready state 1 - opened, server connection was established
        // 2: request recieved
        // 3: processing request...
        // 4 : response recieved, request finished
        xhr.onreadystatechange = function(){
            console.log("State " + xhr.readyState);
            if(xhr.readyState === 4){
                // parse HTTP response body
                var json = JSON.parse(xhr.responseText);
                /*[
                    {
                        "animal_id": "1",
                        "name": "cat",
                        "color": "white"
                    },
                     {
                        "animal_id": "2",
                        "name": "dog",
                        "color": "black"
                    }
        
                ]*/
                console.log("Hello Json: "+ JSON.stringify(json));
                animalloop(json);
            }
        }
        // send the request
        xhr.send();
        console.log("Sent");
}



//get each individual object from response json
function animalloop(json){
    console.log("AnimalLoop");
    for(let animal of json){
        appendAnimal(animal);
    }
}

// Call this function for each animal above to append it to the database
function appendAnimal(animal){
    console.log("appendAnimal");
//create html elements
let row =document.createElement('tr'); // <tr></tr>
let id = document.createElement('td'); //<td></td>
let name = document.createElement('td');//<td></td>
let color = document.createElement('td');//<td></td>

//add text to the html elements
id.innerText = animal.animal_id;//<td>..ID...</td>
name.innerText = animal.name;//<td>...Name....</td>
color.innerText = animal.color;//<td>...Color....</td>

//place the elements inside the row element
row.appendChild(id); // <tr><td>..ID...</td></tr>
row.appendChild(name);// <tr><td>..ID...</td> //<td>...Name....</td> </tr>
row.appendChild(color); // <tr><td>..ID...</td> //<td>...Name....</td> // <tr><td>..ID...</td></tr> </tr>

//Add Details Link
// Create Details Linl
let link = document.createElement('a');
// set onClick attribute, so once user clicks the link, detailsFunction() is executed and animal_id is passed there
link.setAttribute('onClick', `detailsFunction(${animal.animal_id})`);// `${}` template literal
// add text for the link, this will go between <a> </a> tags
link.innerText="Details | ";
////place the link inside the row element
row.appendChild(link);
//Add Details Link Ends

//Add Remove Link
let link2 = document.createElement('a');
link2.setAttribute('onClick', `deleteFunction(${animal.animal_id})`);
link2.innerText="Delete | ";
row.appendChild(link2);
//Add Remove Link Ends


//Add Update Link
let link3 = document.createElement('a');
link3.setAttribute('onClick', `updateFunction1(${animal.animal_id})`);
link3.innerText="Update";
row.appendChild(link3);
//Add Update Link Ends

// Add the row we created above to the table
document.getElementById("list").appendChild(row);// <table id="list"><tr><td>..ID...</td> //<td>...Name....</td> // <tr><td>..ID...</td></tr> </tr></table>
}