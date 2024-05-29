document.getElementById('categories').addEventListener('click', renderCategoriesForms)
const mainElement = document.querySelector('main');

function renderCategoriesForms() {

    mainElement.innerHTML = `
        <form action="#" method="post" id="addCategoryForm" class="categoriesForm"">
            <h2>Додати категорію</h2>
            <div>
                <label for="newCategoryName">Назва категорії:</label>
                <input type="text" id="newCategoryName" name="newCategoryName" required>
            </div>
            <div>
                <button id="create-product-type" type="submit">Додати категорію</button>
            </div>
        </form>
    `

    document.getElementById('create-product-type').addEventListener('click', () => {
        let type = document.getElementById('newCategoryName').value
        fetch(`/api/create-product-type?type=${type}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
    })
}

document.getElementById('users').addEventListener('click', () => {
    fetch('/api/user/get-all', {
        method: 'GET'
    }).then(response => response.json())
        .then(data => {
            if(data.length > 0){
                const databaseContainer = document.createElement('div')
                databaseContainer.className = 'database-table-background'

                databaseContainer.innerHTML = `
                <div class="database-table">
                   <table id="db-table">
                     <tr>
                       <td>#</td>
                       <td>Email</td>
                     </tr>

                   </table>
               </div>
            `

                mainElement.innerHTML = ``
                mainElement.appendChild(databaseContainer)

                const table = document.getElementById('db-table')
                for (let i = 0; i < data.length; i++) {
                    const line = document.createElement('tr')
                    line.innerHTML = `
                        <td>${i}</td>
                        <td>${data[i].email}</td>
                    `
                    table.appendChild(line)
                }
            }
        })
})