async function getOneUser(id) {
    let url = "/api/admin/user/" + id;
    let response = await fetch(url);
    return await response.json();
}