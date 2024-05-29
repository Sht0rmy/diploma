fetch('/api/get-categories', {
    method: 'GET'
}).then(response => response.json())
.then(data => {
    if(data.length > 0){
        const categoriesBlock = document.getElementById('categories')
        for(let i = 0; i < data.length; i++){
            const category = data[i]


            const categoryBlock = document.createElement('div')
            categoryBlock.className = 'category'
            categoryBlock.innerHTML = `
                <img src="/img/empty.jpg" alt="Category 1">
                <div class="category-info">
                    <h3 id='category-${category.id}'>${category.type}</h3>
                </div>
            `
            categoriesBlock.appendChild(categoryBlock)

            document.getElementById(`category-${category.id}`).addEventListener('click', () => {
                window.location.href = `/category/${category.id}`
            })
        }
    }
})



fetch('/api/product/get-popular', {
    method: 'GET'
}).then(response => response.json())
    .then(data => {
        if(data.length > 0){
            const firstPopularLine = document.getElementById('product-cards-first'),
                secondPopularLine = document.getElementById('product-cards-second')
            for(let i = 0; i < data.length; i++){
                const product = data[i]

                const productBlock = document.createElement('div')
                productBlock.className = 'product-card'
                productBlock.innerHTML =`
                    <img src="/images/${product.previewImage}" alt="Product 1">
                    <div class="price-tag">${product.price} грн</div>
                    <h3 class="product-title" id="product-${product.id}">${product.title}</h3>
                `

                if(i < 3)
                    firstPopularLine.appendChild(productBlock)
                else
                    secondPopularLine.appendChild(productBlock)

                document.getElementById(`product-${product.id}`).addEventListener('click', () => {
                    window.location.href = `/product/${product.id}`
                })
            }
        }
})

const search = document.getElementById('search')
search.addEventListener('keypress', function (event){
    if(event.keyCode === 13){
        fetch(`/api/product/search`, {
            method: 'POST',
            body: search.value
        }).then(response => response.json())
            .then(data => {
                if(data.length > 0){
                    document.getElementById('category-block').remove()
                    document.getElementById("popular-block").innerHTML = ''

                    const rec = document.createElement('div')
                    rec.className = 'full-width-image'
                    rec.innerHTML = `
                        <img src="/img/empty.jpg" alt="Фото">
                        <p class="name-full-window">Відеокарта MSI GeForce RTX3060 12Gb <br> VENTUS 2X OC (RTX 3060 VENTUS 2X 12G OC)</p>
                    `
                    document.querySelector(".menu-bar").insertAdjacentElement('afterend',rec)

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
                        `

                        productBlock.append(productElement)
                        count--

                        document.getElementById(`product-${product.id}`).addEventListener('click', () => {
                            window.location.href = `/product/${product.id}`
                        })
                    }
                }else{
                    alert("Товарів не знайдено")
                }
            })
    }
})

function createProductBlock(id){
    const productBlock = document.createElement('div')
    productBlock.className = 'product-cards'
    productBlock.id = `product-cards-${id}`

    document.getElementById('popular-block').appendChild(productBlock)

    return document.getElementById(`product-cards-${id}`)
}