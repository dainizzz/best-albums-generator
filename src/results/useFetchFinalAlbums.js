// // Move to custom hook and pass in a parameter isTriggered
// const effectHelper = () => {
//   // nest inside if is triggered
//   axios
//     .get(`${process.env.REACT_APP_BACKEND_URL}/users/${userId}/albums/ranked`)
//     .then((response) => {
//       console.log("I am in the final album data request!");
//       setFinalAlbums(response.data);
//     })
//     .catch((error) => {
//       console.log("Error:", error);
//     });
// };

// useEffect(() => {
//   effectHelper();
//   // Add isTriggered to this
//   // eslint-disable-next-line react-hooks/exhaustive-deps
// }, []);
// // Map through data in the hook to get only artist & title
