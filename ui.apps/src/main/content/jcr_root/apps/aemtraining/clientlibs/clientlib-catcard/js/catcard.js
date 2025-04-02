window.fetchCatImage = function() {
  const loadingElement = document.getElementById("loading");
  const catImagesContainer = document.getElementById("cat-images-container");
  const count = document.querySelector("[data-count]").getAttribute("data-count") || 1;

  loadingElement.style.display = "block";
  catImagesContainer.innerHTML = ''; // Clear previous images

  fetch(`/bin/catapi?count=${count}`, {
    headers: {
      'x-api-key': 'live_gzQxpWFEnGO8POsVec2wYFwmZZr6eFR8TYAa8lA3Sv9OftcPXZWJjtdOwNp63yIp'
    }
  })
    .then((response) => response.json())
    .then((data) => {
      data.forEach(cat => {
        const newImage = new Image();
        newImage.src = cat.url;
        newImage.alt = "Cat Image";
        newImage.style.width = "200px";
        newImage.style.height = "200px";
        newImage.style.objectFit = "cover";
        newImage.onload = () => {
          loadingElement.style.display = "none";
          catImagesContainer.appendChild(newImage);
        };
      });
    })
    .catch((error) => {
      console.error("Error fetching cat image:", error);
      loadingElement.style.display = "none";
      alert("Failed to fetch cat image. Please try again.");
    });
};

// Fetch an initial cat image when the page loads
document.addEventListener("DOMContentLoaded", fetchCatImage);