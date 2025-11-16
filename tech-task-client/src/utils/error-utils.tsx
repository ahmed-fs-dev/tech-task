export function getErrorMessageForUser(err: any) {
    if (err?.response?.data?.message)
        return err.response.data.message;
    return "Unknown error";
}