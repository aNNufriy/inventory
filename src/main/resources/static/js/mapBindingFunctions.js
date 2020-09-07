        function remapKeysAndValues(){
            document.querySelectorAll('.submit-button').forEach(function (button) {
                button.onclick = function (firedEvent) {
                    let variables = {};
                    let duplicated = false;
                    document.querySelectorAll('#variablerows .value-keeper').forEach(function (row) {
                        let key = row.querySelector("input[name='variable-key']").value;
                        let value = row.querySelector("input[name='variable-value']").value;
                        if(key!=""){
                            if (variables.hasOwnProperty(key)) {
                                duplicated = true
                            } else {
                                variables[key] = value;
                            }
                        }
                    });
                    if(duplicated){
                        firedEvent.preventDefault();
                        alert("One or more keys for ansible variables duplicated, variable keys should be unique!");
                    }else{
                        document.querySelectorAll('#variablerows .value-keeper').forEach(function (row) {
                            row.querySelector("input[name='variable-key']").setAttribute("name","");
                            row.querySelector("input[name='variable-value']").setAttribute("name","");
                        });
                        for (const key in variables) {
                            if (variables.hasOwnProperty(key)) {
                                $("<input />").attr("type", "hidden")
                                    .attr("name", "variables['"+key+"']")
                                    .attr("value", variables[key])
                                    .appendTo("#submitForm");
                            }
                        }
                    }
                }
            })
        }

        function processItemButtonClick(firedEvent) {
            if (firedEvent.target.className.split(" ").indexOf("addItemButton") > -1) {
                let clone = document.querySelector('#variablerow').content.cloneNode(true);
                clone.querySelector('button').onclick = function (firedEvent) {
                    firedEvent.preventDefault();
                    processItemButtonClick(firedEvent);
                };
                document.querySelector('#variablerows').appendChild(clone)
            } else {
                if ($(firedEvent.target).hasClass('removeItemButton')) {
                    $(firedEvent.target.parentElement.parentElement).remove()
                }
            }
        }