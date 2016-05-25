var stompClient = null;
var user = "anon";
var recipient = "all";

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        }

        function connect() {
            user = document.getElementById("name").value;
            
            if(user !== ""){
                document.getElementById("name").style.display = 'none';
                document.getElementById("nameLabel").innerHTML = "Logged in as:" + user +"<br>";
                var socket = new SockJS('/hello');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function(frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/greetings', function(greeting){
                        showGreeting(JSON.parse(greeting.body).content );
                    });
                    stompClient.subscribe('/user/'+user+'/reply', function(msg){
                        showMessage((JSON.parse(msg.body).content), (JSON.parse(msg.body).recipient));
                    });
                });
            }
        }

        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }
            //checking if a div already exists. If not, one is created
        function createDiv(name){
            if($("#" + name +".responseContainer").length == 0){
                var id = "id='" + name +"'";
                 $("#response").append("<div class='responseContainer' " + id + ">Chat opened with: "+name+"</div>");
                 $("#"+name+".responseContainer").hide();
                 var del= '<Button class="btn btn-default" id="del"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> </Button>';
                 $("#discussionList").append('<li id='+ name +'>'+ name + del +'</li>');
                
                //showDiv(name);
            }
        };
        function sendData() {
            var name = user;
            var rec = recipient;
            var msg = document.getElementById('msg').value;
            document.getElementById('msg').value = "";
            var dot = new Date();
            if(rec === "" || rec === "All"|| rec === "all"){
                stompClient.send("/app/hello", {}, JSON.stringify({'sender': name, 'recipient': rec, 'msg':msg, 'createdOn': dot}));
                console.log("sent to hello");
            }
            else{
                stompClient.send("/app/msg", {}, JSON.stringify({'sender': name, 'recipient': rec, 'msg':msg, 'createdOn': dot}));
                console.log("sent to msg");
                showMessage((dot + "YOU: " + msg), rec);
            }
        }

        function showGreeting(message) {
            console.log("GREETING RESPONSE LOL");
            $("#all.responseContainer").append("<br>");
            $("#all.responseContainer").append(document.createTextNode(message));
            $("#all.responseContainer").css("wordWrap: 'breakWord;");
            $("#discussionList #all").css({'color':'green'});
        }
        //function that shows only current div
        function showDiv(div){
            recipient = div;
            $(".responseContainer").hide();
            $("#"+div+".responseContainer").show();
            $("#curChat").text("Current chat: " + div);
        }
        
        //The message and its target div is received. If the target div does not exists, it is created.
        function showMessage(message, reci) {
            console.log(message);
            //creating new div if necessary
            createDiv(reci);
            //Showing message on div
            $("#"+reci+".responseContainer").append("<br>");
            $("#"+reci+".responseContainer").append(document.createTextNode(message));
            $("#"+reci+".responseContainer").css({'wordWrap':'breakWord'});
            $("#discussionList #"+reci).css({'color':'green'});   
        }
        $(document).ready(function () {
            $("#curChat").text("Current chat: all");
            //changing currently active discussion
            $("#Discussions").on("click","li",function (){
                $(this).css({'color':'black'});
                showDiv(this.id);
            });
            
            //add new discussion, if it does not yet exists 
            $("#addDisc").click(function(){
                var recipient = $("#recep").val();
                //The input must not be empty, it must not already exist and it cannot be the users own name.
                if(recipient != "" && $("#"+recipient+".responseContainer").length == 0 && recipient != user){
                    var del = '<Button class="btn btn-default" id="del"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span> </Button>';
                    $("#discussionList").append('<li id='+ recipient +'>'+ recipient + del +'</li>');
                    var id = "id='" + recipient +"'";
                    $("#response").append("<div class='responseContainer' " + id + ">New Chat opened with user: "+recipient+"</div>");
                    showDiv(recipient);
                }
            });
            $("ul").on("click","#del",function (){
                console.log("delete");
                $(this).parent().remove();
                console.log("#"+$(this).parent().attr('id')+".responseContainer");
                $("#"+$(this).parent().attr('id')+".responseContainer").remove();
            })
        });