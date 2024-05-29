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