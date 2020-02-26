function MagnifyQuestion(){

}

/**
 * Change request URL with the search method.
 * @param method_name A string contains the name of the search method
 */
function ChangeSearchMethod(method_name){
    document.getElementById("search_form").setAttribute("action", "/search/" + method_name);
    let place_holder = "Enter key word here";
    if (method_name === "by_tags"){
        place_holder = "Enter categories here, separate by space"
    }
    else if (method_name === "by_id"){
        place_holder = "Enter question ID here"
    }
    document.getElementById("search_bar").setAttribute("placeholder", place_holder);

}