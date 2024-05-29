fetch('/api/user/get-my-wish', {
    method: 'GET'
}).then(response => response.json())
    .then(data => {
        console.log(data)
        if(data.length > 0){
            const basketBlock = document.getElementById('shop-product-list')

            for(let i = 0; i < data.length; i++){
                const product = data[i]

                const productBlock = document.createElement('li')
                productBlock.className = 'shop-product-item'
                productBlock.innerHTML = `
                <img src="/images/${product.previewImage}" alt="Товар 1">
                <div class="shop-product-info">
                    <p class="shop-product-name" id="product-${product.id}">${product.title}</p>
                    <p class="shop-product-price">${product.price} грн.</p>
                </div>
            `

                basketBlock.appendChild(productBlock)

                document.getElementById(`product-${product.id}`).addEventListener('click', () => {
                    window.location.href = `/product/${product.id}`
                })
            }
        }
    })