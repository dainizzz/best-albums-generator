export const getApiUrl = (url, params) => {
  const regexParams = new RegExp(Object.keys(params).join("|"), "gi");
  return url.replace(regexParams, (param) => params[param]);
};
