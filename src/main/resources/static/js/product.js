const productId = document.getElementById("productId").getAttribute("data-product-id");

fetch('/api/product/get-top-products', {
    method: 'GET'
}).then(response => response.json())
.then(data => {
    if(data.length > 0){
        const recProductBlock = document.getElementById('recomended-card')

        for(let i = 0; i < data.length; i++){
            const product = data[i]

            const productElement = document.createElement('div')
            productElement.className = 'product-card-recomended'
            productElement.innerHTML = `
                <img src="/images/${product.previewImage}" alt="Product 1">
                <div class="price-tag recom-tag">${product.price} грн</div>
                <h3 class="product-title">${product.title}</h3>
            `

            recProductBlock.appendChild(productElement)
        }
    }
})

document.getElementById('save-button').addEventListener('click', () => {
    fetch(`/api/user/add-to-wish?productId=${productId}`, {
        method: 'POST'
    }).then(response => response.status)
        .then(status => {
            if(status === 200)
                alert("Успішно додано до списку")
        })
})

document.getElementById('buy-button').addEventListener('click', () => {
    fetch(`/api/user/add-to-basket?productId=${productId}`, {
        method: 'POST'
    }).then(response => response.status)
        .then(status => {
            if(status === 200)
                alert("Успішно додано до кошика")
        })
})



fetch(`/api/review/get?productId=${productId}`, {
    method: 'GET'
}).then(response => response.json())
    .then(data => {
        if(data.length > 0){
            const reviewsList = document.getElementById('reviews-list');
            for(let i = 0; i < data.length; i++){
                const review = data[i]
                const reviewElement = document.createElement('div')
                reviewElement.className = 'review'
                reviewElement.innerHTML = `
                    <div class="name">${review.name}</div>
                    <div class="text">${review.text}</div>
                    <div class="rating">${'★'.repeat(review.rating)}${'☆'.repeat(5 - review.rating)}</div>
                `
                reviewsList.appendChild(reviewElement)
            }
        }
    })

document.getElementById('new-review-button').addEventListener('click', () => {
    const name = prompt('Введіть ваше ім\'я');
    const text = prompt('Введіть текст відгуку');
    let rating = parseInt(prompt('Введіть оцінку (0-5)'));
    if (isNaN(rating) || rating < 0 || rating > 5) {
        rating = 0;
    }

    const dataToSend = {
        name, rating, text
    }

    if (name && text) {
        fetch(`/api/review/create?productId=${productId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
               body: JSON.stringify(dataToSend)
        }).then(response => response.text())
            .then(data => {
                alert(data)
                window.location.href = `/product/${productId}`
            })
        }
    })

fetch(`/api/question/get?productId=${productId}`, {
    method: 'GET'
}).then(response => response.json())
    .then(data => {
        if(data.length > 0){
            const questionList = document.getElementById('qa-list')

            let role;
            fetch('/api/user/get-me', {
                method: 'GET'
            }).then(response => response.json())
                .then(user =>{
                    role = user.role
                    for(let i = 0; i < data.length; i++) {
                        const question = data[i]

                        const questionBlock = document.createElement('div')
                        questionBlock.className = 'qa'
                        questionBlock.id = `qa-${i}`
                        questionBlock.innerHTML = ` 
                            <div class="question">
                                <div class="name">${question.name}</div>
                                <div class="text">${question.question}</div>
                            </div>
                        `

                        questionList.appendChild(questionBlock)

                        if (question.answer) {
                            const qaBlock = document.getElementById(`qa-${i}`)

                            const answerBlock = document.createElement('div')
                            answerBlock.className = 'answer'
                            answerBlock.innerHTML = `
                                <div class="admin">Адміністратор</div>
                                <div class="text">${question.answer}</div>
                            `

                            qaBlock.appendChild(answerBlock)
                        }else if(role === 'ADMIN') {
                            const answerButton = document.createElement('div')
                            answerButton.className = 'answer-button'
                            answerButton.innerHTML = `
                                <button id="answer-${i}">Відповісти</button>
                            `
                            const qaBlock = document.getElementById(`qa-${i}`)
                            qaBlock.appendChild(answerButton)

                            document.getElementById(`answer-${i}`).addEventListener('click', () => {
                                const answer = prompt('Введіть відповідь на питання')
                                if(answer){
                                    fetch(`/api/question/answer?questionId=${question.id}`, {
                                        method: 'POST',
                                        headers: {
                                            'Content-Type': 'application/json'
                                        },
                                        body: JSON.stringify(answer)
                                    }).then(response => response.text())
                                        .then(text => {
                                            alert(text)
                                            window.location.href = `/product/${productId}`
                                        })
                                }
                            })
                        }
                    }
                })
        }
    })


document.getElementById('new-qa-button').addEventListener('click', () => {
    const name = prompt('Введіть ваше ім\'я');
    const question = prompt('Введіть текст питання');


    const dataToSend = {
        name, question
    }

    if (name && question) {
        fetch(`/api/question/create?productId=${productId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataToSend)
        }).then(response => response.text())
            .then(data => {
                alert(data)
                window.location.href = `/product/${productId}`
            })
    }
})