import axios from "axios";

const errorHandler = (error) => {
  if (error.response.status === 403) window.location.replace("/");
  // cannot connect to server
};

export const AccountAPI = {
  getAccount: (username, password) =>
    axios
      .get("/api/account", { params: { username, password } })
      .catch(errorHandler),
  postAccount: (username, password) =>
    axios
      .post("/api/account", { data: { username, password } })
      .catch(errorHandler),
  putAccount: (newAccountData) =>
    axios.put("/api/account", { data: newAccountData }).catch(errorHandler),
};

export const BlockAPI = {
  getBlocks: (username) =>
    axios.get("/api/block", { params: { username } }).catch(errorHandler),
  postBlock: (block) =>
    axios.post("/api/block", { data: { block } }).catch(errorHandler),
  putBlocks: (blocks) =>
    axios.put("/api/block", { data: { blocks } }).catch(errorHandler),
  deleteBlocks: (blocksId) =>
    axios.delete("/api/block", { data: { blocksId } }).catch(errorHandler),
};

export const TermAPI = {
  postTerms: (content) =>
    axios.post("/api/term", { data: { content } }).catch(errorHandler),
};

export const TranslationAPI = {
  postTranslations: (content) =>
    axios.post("/api/translation", { data: { content } }).catch(errorHandler),
};

export const SummaryAPI = {
  postSummary: (content) =>
    axios.post("/api/summary", { data: { content } }).catch(errorHandler),
};
