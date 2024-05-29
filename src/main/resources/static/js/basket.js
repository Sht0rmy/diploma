fetch('/api/user/get-basket', {
    method: 'GET'
}).then(response => response.json())
.then(data => {
    if(data.length > 0){
        const basketBlock = document.getElementById('shop-product-list')

        for(let i = 0; i < data.length; i++){
            const product = data[i]

            const productBlock = document.createElement('li')
            productBlock.className = 'shop-product-item'
            productBlock.innerHTML = `
                <img src="/images/${product.previewImage}" alt="Товар 1">
                <div class="shop-product-info">
                    <p class="shop-product-name">${product.title}</p>
                    <p class="shop-product-price">${product.price} грн.</p>
                </div>
            `

            basketBlock.appendChild(productBlock)
        }
    }
})