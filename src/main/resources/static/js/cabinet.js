fetch('/api/user/get-me', {
    method: 'GET'
}).then(response => response.json())
.then(data => {
    const userBlock = document.getElementById('product-container')

    const block = document.createElement('div')
    block.className = 'product-details'
    block.innerHTML = `
        <div class="product-guaranty" style="font-size:25px">${data.email}</div>
        <div class="by-btn-product"><button class="by-on-produst" id="by-on-produst">Список бажаного</button></div>    
    `

    userBlock.appendChild(block)

    document.getElementById('by-on-produst').addEventListener('click', () => {
        window.location.href = `/wishlist`
    })
})