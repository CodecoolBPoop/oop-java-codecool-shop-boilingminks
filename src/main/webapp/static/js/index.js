var addButtons;

addButtons = document.getElementsByName('addToCart');

for (let addButton of addButtons) {
    addButton.addEventListener('click', function () {
        let buttonId = this.id;
        console.log(buttonId);
    })

}
