import streamlit as st
from azure.storage.blob import BlobClient, ContainerClient
import os


def upload():
    st.title("Cloud Computing Lab - Mini Project")
    connection_string = 'DefaultEndpointsProtocol=https;AccountName=miniprojdb;AccountKey=ws++RxvOwfEH/JReGNmHZq15Uj7vlen0ZeELIwOPmqbp9z39vyCc3eMNb3pXKHfFJnDY1MAGHM5TEkbLuLjVaA==;EndpointSuffix=core.windows.net'
    uploaded_file = st.file_uploader('',type= '.pdf',accept_multiple_files=False)
    if st.button("Upload"):
        blob_client = BlobClient.from_connection_string(connection_string, container_name="photos", blob_name=uploaded_file.name)
        blob_client.upload_blob(uploaded_file.read())
        st.sidebar.info("Successfully Uploaded")

def download():
    st.title("Cloud Computing Lab - Mini Project")
    connection_string = 'DefaultEndpointsProtocol=https;AccountName=miniprojdb;AccountKey=ws++RxvOwfEH/JReGNmHZq15Uj7vlen0ZeELIwOPmqbp9z39vyCc3eMNb3pXKHfFJnDY1MAGHM5TEkbLuLjVaA==;EndpointSuffix=core.windows.net'
    container_client = ContainerClient.from_connection_string(conn_str=connection_string, container_name="photos")
    blobs = container_client.list_blobs()
    for blob in blobs:
        st.markdown(blob.name)
        if st.button("Download", key=blob.name):
            blob_client = BlobClient.from_connection_string(connection_string, container_name="photos", blob_name=blob.name)
            bytes = blob_client.download_blob().readall()
            download_file_path = os.path.join('C:\\Users\\Warren\\Downloads\\', blob.name)
            os.makedirs(os.path.dirname(download_file_path), exist_ok=True)       
            with open(download_file_path, "wb") as file:
                file.write(bytes)

if __name__ == '__main__':
    nav = st.sidebar.radio("", ["Upload", "Download"])
    if nav == "Upload":
        upload()
    if nav == "Download":
        download()