configs = {"fs.azure.account.auth.type": "OAuth",
           "fs.azure.account.oauth.provider.type": "org.apache.hadoop.fs.azurebfs.oauth2.ClientCredsTokenProvider",
           "fs.azure.account.oauth2.client.id": "7eabd50f-3e5e-4fa2-acfd-9ace8d6c77f2",
           "fs.azure.account.oauth2.client.secret": "l55B_~lw5OK80t.kSw.~7I~CHku4WIsh68",
           "fs.azure.account.oauth2.client.endpoint": "https://login.microsoftonline.com/d0c2778c-2bb0-4cf8-9233-9ca1afbeed47/oauth2/token",
           "fs.azure.createRemoteFileSystemDuringInitialization": "true"}

# Optionally, you can add <directory-name> to the source URI of your mount point.
dbutils.fs.mount(
  source = "abfss://sahan9@sahan9.dfs.core.windows.net/",
  mount_point = "/mnt/sahan9/",
  extra_configs = configs)

display(dbutils.fs.ls('/mnt/sahan9'))