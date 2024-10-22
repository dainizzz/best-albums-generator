export const BACKEND_URL = process.env.REACT_APP_BACKEND_URL;

export const DEFAULT_PARAMS = {
  ":process": BACKEND_URL,
};

export const USER_GAME_ROUND_URL =
  ":process/users/:currentUserId/games/round/:currentRound";

// Future updates will have all URLs here
