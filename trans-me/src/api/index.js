import axios from "axios";

const errorHandler = (error) => {
  if (error.response.status === 403) window.location.replace("/");
};

export const AccountAPI = {
  getAccount: (username, password) =>
    axios
      .get("/api/account", { data: { username, password } })
      .catch(errorHandler),
  postAccount: (username, password) =>
    axios
      .post("/api/account", { data: { username, password } })
      .catch(errorHandler),
  putAccount: (newAccountData) =>
    axios.put("/api/account", { data: newAccountData }).catch(errorHandler),
};

export const BlockAPI = {
  getBlocks: (blocksId) =>
    axios.get("/api/block", { data: { blocksId } }).catch(errorHandler),
  postBlock: (block) =>
    axios.post("/api/block", { data: { block } }).catch(errorHandler),
  putBlocks: (blocks) =>
    axios.put("/api/block", { data: { blocks } }).catch(errorHandler),
  deleteBlock: (blocksId) =>
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
