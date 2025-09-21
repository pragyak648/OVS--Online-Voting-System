document.getElementById("loginForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch("http://localhost:8080/voter/login", {  // make sure /voter/login matches your backend endpoint
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email: email, password: password })
    })
    .then(response => response.json())
    .then(data => {
        if(data.success){
            alert("Login successful!");
            // redirect or do something
        } else {
            alert("Invalid credentials");
        }
    })
    .catch(error => {
        console.error("Login failed:", error);
        alert("Network/API error!");
    });
});
