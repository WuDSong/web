let num = 0
let inputNum = document.querySelector(".num")
console.log(num.value);
let minusButton = document.querySelector(".minus")
let plusButton = document.querySelector(".plus")
minusButton.addEventListener("click", () => {
    if (num > 0)
        num--
    ChangeNum(num)
})

plusButton.addEventListener("click", () => {
    if (num < 100)
        num++
    ChangeNum(num)
})

inputNum.addEventListener("input", () => {
    num = inputNum.value
})

function ChangeNum(i) {
    inputNum.value = i;
}



