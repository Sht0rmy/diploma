
fetch('/api/blog/get-all', {
    method: 'GET'
}).then(response => response.json())
    .then(data => {
        console.log(data)
        if(data.length > 0) {
            const blogContainer = document.getElementById('blog-container')

            for(let i = 0; i < data.length; i++){
                const post = data[i]

                const postBlock = document.createElement('div')
                postBlock.className = 'post'
                postBlock.innerHTML = `
                    <img src="/images/${post.image}">
                    <p>${post.text}</p>
                `

                blogContainer.appendChild(postBlock)
            }
        }
    })