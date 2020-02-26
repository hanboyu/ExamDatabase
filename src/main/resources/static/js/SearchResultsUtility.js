var question_container = document.getElementById("magnified_question_container");
var close_button = close_button = document.getElementById("magnified_question_close_button");

// When the user clicks on <span> (x), close the modal
close_button.onclick = function() {
    question_container.style.display = "none";};
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target === question_container) {
        question_container.style.display = "none";
    }
};

function MagnifyQuestion(id){
    question_container.style.display = "block";
}

function ExpendQuestion(question_id) {
    let q = document.getElementById(question_id);
    let choices = q.getElementsByClassName("rq_choices_container")[0];
    choices.style.display = "block";
    let arrow = q.getElementsByClassName("arrow");
    arrow[0].style.display = "none";
    arrow[1].style.display = "inline-block";
}

function CollapseQuestion(question_id){
    let q = document.getElementById(question_id);
    let choices = q.getElementsByClassName("rq_choices_container")[0];
    choices.style.display = "none";
    let arrow = q.getElementsByClassName("arrow");
    arrow[0].style.display = "inline-block";
    arrow[1].style.display = "none";
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