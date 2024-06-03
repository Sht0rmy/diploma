document.getElementById("accept").addEventListener('click', () => {
    const name = document.getElementById('name').value
    const minPrice = document.getElementById('min-price').value
    const maxPrice = document.getElementById('max-price').value
    const category = document.getElementById('category').value

    fetch(`/api/product/advanced-search?name=${name}&minPrice=${minPrice}&maxPrice=${maxPrice}&category=${category}`,{
        method: 'GET'
    }).then(response => response.json())
        .then(data => {
            if(data.length > 0){
                document.getElementById('container-wrapper').remove()
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
})

function createProductBlock(id){
    const productBlock = document.createElement('div')
    productBlock.className = 'product-cards'
    productBlock.id = `product-cards-${id}`

    document.getElementById('popular-block').appendChild(productBlock)

    return document.getElementById(`product-cards-${id}`)
}