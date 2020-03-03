var choice_conuter = 1;


function add_tag() {
    let new_tag = prompt("New category:");
    if ('content' in document.createElement('template')) {
        let TagsList = document.getElementById("tagsList");
        let temp = document.getElementById("tag_template").content.cloneNode(true);
        temp.querySelector("span").innerHTML = new_tag;
        temp.querySelector("textarea").defaultValue = new_tag;
        temp.querySelector("li").setAttribute("id", "tag_" + new_tag);
        temp.querySelector("button").setAttribute("onclick", "deleteTages('tag_" + new_tag + "')")
        TagsList.appendChild(temp);
    } else {
        // Templates are not supported.
        console.log("Templates are not supported");
    }
}

function DeleteTag(tag_id) {
    let target_tag = document.getElementById(tag_id);
    target_tag.parentNode.removeChild(target_tag);
}

function add_choice() {
    if ('content' in document.createElement('template')){
        let choice_list = document.getElementById("choices_list");
        let new_choice = document.getElementById("choice_template").content.cloneNode(true);
        new_choice.querySelector("li").setAttribute("id", "choice_" + choice_conuter);
        new_choice.querySelector("input").setAttribute("id", "choice_answer" + choice_conuter);
        new_choice.querySelectorAll("i")[1].setAttribute("onclick", "DeleteChoice('choice_" + choice_conuter + "')") ;
        choice_list.appendChild(new_choice);
    }
    else{
        // Templates are not supported.
        console.log("Templates are not supported");
    }
    choice_conuter++;
}

function DeleteChoice(choice_id) {
    let target_choice = document.getElementById(choice_id);
    target_choice.parentNode.removeChild(target_choice);
}



