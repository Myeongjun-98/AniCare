        const etcCheckbox = document.getElementById("etcCheckbox");
        const etcSymptom = document.getElementById("etcSymptom");

        etcCheckbox.addEventListener("change", () => {
            etcSymptom.disabled = !etcCheckbox.checked;

            if (etcCheckbox.checked) {
                etcSymptom.disabled = false;
                etcSymptom.focus();
            }else {
                etcSymptom.disabled = true;
                etcSymptom.value = "";
            }
    });