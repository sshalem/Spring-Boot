import { getElement } from './utils.js';

const ulContainer = getElement('.aside-ul-container');
let toggleArray = [];

ulContainer.addEventListener('click', (event) => {
    let listOfClasses = event.target.classList;
    let name = event.target.dataset.name;

    // I use this n order to make only the clicked menue to be open
    // and the others to be closed
    // Here i check if the nmae is 'undefined'
    // Why?
    // Because I might click on area that is not been captured , thus it will get an undefined value
    // ANd I don't want to add undifined to the Array of 'toggleArry'
    if (name === undefined) {
        return;
    }
    toggleArray.push(name);

    const inputDataId = getElement(`#input-data-${name}`);
    const arrowStatus = getElement(`#${name}-arrow-status`);

    const isDown = isArrow(name, listOfClasses, 'down');
    const isUp = isArrow(name, listOfClasses, 'up');

    if (isDown && arrowStatus.classList.contains('down')) {
        closeExistingMenu(toggleArray);
        arrowStatus.classList.remove('down');
        arrowStatus.classList.add('up');
        arrowStatus.innerHTML = `<i class="fas fa-angle-up" data-name="${name}"></i>`;
        inputDataId.style.transition = 'height 1s';
        inputDataId.style.height = '3rem';
    } else if (isUp && arrowStatus.classList.contains('up')) {
        toggleArray.shift();
        arrowStatus.classList.remove('up');
        arrowStatus.classList.add('down');
        arrowStatus.innerHTML = `<i class="fas fa-angle-down" data-name="${name}"></i>`;
        inputDataId.style.transition = 'height 1s';
        inputDataId.style.height = '0';
    }
});

const isArrow = (name, listOfClasses, state) => {
    if (
        listOfClasses.contains('aside-li') ||
        listOfClasses.contains('aside-li-description') ||
        listOfClasses.contains(`aside-li-${name}`) ||
        listOfClasses.contains('aside-li-arrow') ||
        listOfClasses.contains(`fa-angle-${state}`)
    ) {
        return true;
    } else {
        return false;
    }
};

function closeExistingMenu(toggleArray) {
    if (toggleArray.length === 1) {
        return;
    } else {
        //this removes the first elemt of the Array
        let element = toggleArray.shift();

        const inputDataId = getElement(`#input-data-${element}`);
        const arrowStatus = getElement(`#${element}-arrow-status`);

        arrowStatus.classList.remove('up');
        arrowStatus.classList.add('down');

        arrowStatus.innerHTML = `<i class="fas fa-angle-down" data-name="${element}"></i>`;
        inputDataId.style.transition = 'height 1s';
        inputDataId.style.height = '0';
    }
}
