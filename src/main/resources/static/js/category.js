const categoryId = document.getElementById("categoryId").getAttribute("data-category-id");

fetch(`/api/product/get-by-type?id=${categoryId}`, {
    method: 'GET'
}).then(response => response.json())
.then(data => {
    if(data.length > 0){
        let count = 0
        let productBlock
        for(let i = 0; i < data.length; i++){
            const product = data[i]

            if(count === 0)
                productBlock = createProductBlock(i)

            const productElement = document.createElement('div')
            productElement.className = `product-card`
            productElement.innerHTML = `
                <img src="/images/${product.previewImage}" alt="Product 1">
                <div class="price-tag">${product.price} грн</div>
                <h3 class="product-title" id="product-${product.id}">${product.title}</h3>
                <div class="rating" id="rating-${product.id}"></div>
            `

            productBlock.append(productElement)
            count--

            const ratingElement = document.getElementById(`rating-${product.id}`)
            const filledStars = '★'.repeat(product.rating)
            const emptyStars = '☆'.repeat(5 - product.rating)
            ratingElement.innerHTML = filledStars + emptyStars

            document.getElementById(`product-${product.id}`).addEventListener('click', () => {
                window.location.href = `/product/${product.id}`
            })
        }
    }
})

function createProductBlock(id){
    const productBlock = document.createElement('div')
    productBlock.className = 'product-cards'
    productBlock.id = `product-cards-${id}`

    document.getElementById('popular-block').appendChild(productBlock)

    return document.getElementById(`product-cards-${id}`)
}