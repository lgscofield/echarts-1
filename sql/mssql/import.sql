/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
USE [echarts]
GO
/****** Object:  Table [dbo].[ServiceCodes]    Script Date: 05/25/2010 14:13:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ServiceCodes](
	[Service] [varchar](3) NOT NULL,
	[Description] [varchar](50) NULL,
	[TemplateID] [varchar](15) NULL,
 CONSTRAINT [PK_ServiceCodes] PRIMARY KEY CLUSTERED 
(
	[Service] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'110', N'Pharmacological Mgt. - Evaluation', N'104')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'120', N'Diagnostic Assessment', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'210', N'BH Counseling and Therapy - Individual', N'102')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'220', N'BH Counseling and Therapy - Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'235', N'ODADAS (AOD Family/Couple Counseling)', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'390', N'CPST Individual - Primary', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'391', N'CPST Individual - Secondary', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'392', N'CPST Group - Primary', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'393', N'CPST Group - Secondary', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'394', N'CPST Mont. Co.', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'395', N'CPST Intake', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'437', N'Pharmacological Mgt. - Assessment', N'103')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'440', N'Pharmacological Mgt. - Ongoing', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'441', N'Pharmacological Mgt. - Aftercare', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'510', N'Doors Job Development', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'511', N'Doors Job Coaching', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'512', N'Doors Benefits Consultation', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'513', N'SupEmp - Voc Services Individual', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'514', N'SupEmp - Macsis (CSP Individual)', N'101')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'520', N'Doors Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'521', N'SupEmp - BVR Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'522', N'SupEmp - BSVI Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'523', N'SupEmp - Voc Services Group', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'524', N'SupEmp - Macsis (CSP Group)', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'710', N'ODADAS - Individual AOD Counseling', N'102')
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'720', N'ODADAS - Group AOD Counseling', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'913', N'SupEmp - NON-BILLABLE', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'982', N'Not able to attend', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'983', N'Kid refuses services', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'984', N'MIA/AWOL', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'985', N'NO SHOW (INCARCERATION)', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'988', N'Inappropriate For Services', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'989', N'No Show For Intake', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'996', N'NO SHOW (HOSPITALIZATION)', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'997', N'CANCELLATION', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'998', N'NO SHOW', NULL)
INSERT [dbo].[ServiceCodes] ([Service], [Description], [TemplateID]) VALUES (N'999', N'NON-BILLABLE', NULL)
/****** Object:  Table [dbo].[Role]    Script Date: 05/25/2010 14:13:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[role_id] [int] NOT NULL,
	[role_name] [varchar](25) NULL,
	[permission] [varbinary](4) NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (1, N'Administrator', 0x00000001)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (2, N'Psychiatrist', 0x00000002)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (3, N'Nurse', 0x00000003)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (4, N'Comm Support Specialist', 0x00000004)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (5, N'Therapist', 0x00000005)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (6, N'Res Support Specialist', 0x00000006)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (7, N'Clinical Administrator', 0x00000007)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (8, N'Human Resources', 0x00000008)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (9, N'Accounts Recievable', 0x00000009)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (10, N'General Admin', 0x0000000A)
INSERT [dbo].[Role] ([role_id], [role_name], [permission]) VALUES (11, N'Unprivileged', NULL)
/****** Object:  Table [dbo].[link]    Script Date: 05/25/2010 14:13:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[link](
	[name] [varchar](60) NOT NULL,
	[url] [varchar](75) NOT NULL,
	[header] [varchar](30) NULL,
	[sort_order] [int] NULL,
 CONSTRAINT [PK_Form_list] PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[link] ([name], [url], [header], [sort_order]) VALUES (N'Community Psychiatric Supportive Treatment Progress Note', N'http://ewsql/echarts-asp/101CPSTEdit.asp', N'Progress Notes', 2)
INSERT [dbo].[link] ([name], [url], [header], [sort_order]) VALUES (N'General Mental Health Services Acknowledgement', N'http://ewsql/echarts-asp/105GMHServiceAck.asp', N'Miscellaneous', 10)
INSERT [dbo].[link] ([name], [url], [header], [sort_order]) VALUES (N'Individual Progress Note', N'http://ewsql/echarts-asp/102IPNEdit.asp', N'Progress Notes', 3)
INSERT [dbo].[link] ([name], [url], [header], [sort_order]) VALUES (N'ISP Review', N'http://ewsql/echarts-asp/noForm.asp', N'Individualized Service Plan', 9)
INSERT [dbo].[link] ([name], [url], [header], [sort_order]) VALUES (N'ISP Update', N'http://ewsql/echarts-asp/noForm.asp', N'Individualized Service Plan', 8)
INSERT [dbo].[link] ([name], [url], [header], [sort_order]) VALUES (N'New ISP', N'http://ewsql/echarts-asp/GandO.asp', N'Individualized Service Plan', 7)
INSERT [dbo].[link] ([name], [url], [header], [sort_order]) VALUES (N'Pharmacological Management Progress Note', N'http://ewsql/echarts-asp/104PharmEdit.asp', N'Progress Notes', 5)
INSERT [dbo].[link] ([name], [url], [header], [sort_order]) VALUES (N'Pharmacological Management/Nursing Progress Note', N'http://ewsql/echarts-asp/103PM-NPNEdit.asp', N'Progress Notes', 4)
/****** Object:  Table [form].[ISP_ServiceDescriptions]    Script Date: 05/25/2010 14:13:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [form].[ISP_ServiceDescriptions](
	[ID] [numeric](18, 0) NOT NULL,
	[ServiceDescriptions] [varchar](30) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(2 AS Numeric(18, 0)), N'MH Assessment')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(4 AS Numeric(18, 0)), N'MH Pharm. Mgmt')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(5 AS Numeric(18, 0)), N'BH Individ Counseling/Ther')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(6 AS Numeric(18, 0)), N'BH Group Counseling/Ther')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(7 AS Numeric(18, 0)), N'CPST Individ Svcs')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(8 AS Numeric(18, 0)), N'CPST Group Svcs')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(20 AS Numeric(18, 0)), N'------------------------------')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(21 AS Numeric(18, 0)), N'AoD Crisis Intervention')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(25 AS Numeric(18, 0)), N'AoD Individual Counseling')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(26 AS Numeric(18, 0)), N'AoD Group Counseling')
INSERT [form].[ISP_ServiceDescriptions] ([ID], [ServiceDescriptions]) VALUES (CAST(27 AS Numeric(18, 0)), N'AoD Case Management')
/****** Object:  Table [dbo].[DiagnosisAxis2]    Script Date: 05/25/2010 14:13:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiagnosisAxis2](
	[ICD9] [nvarchar](8) NULL,
	[Description] [nvarchar](50) NULL,
	[DSMIV] [nvarchar](8) NULL,
	[508k] [bit] NOT NULL,
	[TabDiagnosis] [nvarchar](50) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.0  ', N' PARANOID PERSONALITY  ', N'301.0  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.20 ', N' SCHIZOID PERSONALITY NOS  ', N'301.20 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.22 ', N' SCHIZOTYPAL PERSON DIS  ', N'301.22 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.4  ', N' OBSESSIVE-COMPULSIVE PERSONALITY DIS  ', N'301.4  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.50 ', N' HISTRIONIC PERSON NOS  ', N'301.50 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.6  ', N' DEPENDENT PERSONALITY  ', N'301.6  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.7  ', N' ANTISOCIAL PERSONALITY  ', N'301.7  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.81 ', N' NARCISSISTIC PERSONALITY  ', N'301.81 ', 0, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.82 ', N' AVOIDANT PERSONALITY DIS  ', N'301.82 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.83 ', N' BORDERLINE PERSONALITY  ', N'301.83 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.9  ', N' PERSONALITY DISORDER NOS  ', N'301.9  ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'317  ', N' MILD MENTAL RETARDATION  ', N'317  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'318.0  ', N' MOD MENTAL RETARDATION  ', N'318.0  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'318.1  ', N' SEVERE MENTAL RETARDAT  ', N'318.1  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'318.2  ', N' PROFOUND MENTAL RETARDAT  ', N'318.2  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'319  ', N' MENTAL RETARDATION NOS  ', N'319  ', 0, N'Mental Retardation')
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.89 ', N' BORDERLINE INTELLECTUAL FUNCTIONING', N'V62.89 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis2] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V71.09 ', N' NO DIAGNOSIS ON AXIS II', N'V71.09 ', 0, NULL)
/****** Object:  Table [dbo].[DiagnosisAxis1]    Script Date: 05/25/2010 14:13:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiagnosisAxis1](
	[ICD9] [nvarchar](8) NULL,
	[Description] [nvarchar](50) NULL,
	[DSMIV] [nvarchar](8) NULL,
	[508k] [bit] NOT NULL,
	[TabDiagnosis] [nvarchar](50) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'290.40 ', N' VASCULAR DEMENTIA,UNCOMP  ', N'290.40 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'290.41 ', N' VASC DEMENTIA W DELIRIUM  ', N'290.41 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'290.42 ', N' VASC DEMENTIA W DELUSION  ', N'290.42 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'290.43 ', N' VASC DEMENTIA W DEPRESSN  ', N'290.43 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.0  ', N' DELIRIUM TREMENS  ', N'291.0  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.1  ', N' ALCOHOL AMNESTIC DISORDR  ', N'291.1  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.2  ', N' ALCOHOL PERSIST DEMENTIA  ', N'291.2  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.3  ', N' ALCOH PSY DIS W HALLUCIN  ', N'291.3  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.5  ', N' ALCOH PSYCH DIS W DELUS  ', N'291.5  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.81 ', N' ALCOHOL WITHDRAWAL  ', N'291.81 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.89 ', N' ALCOHOL MENTAL DISOR NEC  ', N'291.89 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'291.9  ', N' ALCOHOL MENTAL DISOR NOS  ', N'291.9  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.0  ', N' DRUG WITHDRAWAL  ', N'292.0  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.11 ', N' DRUG PSYCH DISOR W DELUS  ', N'292.11 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.12 ', N' DRUG PSY DIS W HALLUCIN  ', N'292.12 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.81 ', N' DRUG-INDUCED DELIRIUM  ', N'292.81 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.82 ', N' DRUG PERSISTING DEMENTIA  ', N'292.82 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.83 ', N' DRUG PERSIST AMNESTC DIS  ', N'292.83 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.84 ', N' DRUG-INDUCED MOOD DISORD  ', N'292.84 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.89 ', N' DRUG MENTAL DISORDER NEC  ', N'292.89 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'292.9  ', N' DRUG MENTAL DISORDER NOS  ', N'292.9  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'293.0  ', N' DELIRIUM D/T OTHER COND  ', N'293.0  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'293.81 ', N' PSY DIS W DELUS OTH DIS  ', N'293.81 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'293.82 ', N' PSY DIS W HALLUC OTH DIS  ', N'293.82 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'293.83 ', N' MOOD DISORDER DUE TO MEDICAL CONDITION', N'293.83 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.0  ', N' AMNESTIC DISORD OTH DIS  ', N'294.0  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.1  ', N' DEMENTIA W/OUT BEHAV. DISTURB', N'294.1  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.11 ', N' DEMENTIA W BEHAVIOR DIST  ', N'294.11 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.8  ', N' DEMENTIA NOS/AMNESTIC DIS NOS', N'294.8  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'294.9  ', N' COGNITIVE DIS NOS', N'294.9  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.10 ', N' SCHIZOPHRENIA, DISORGANIZED', N'295.10 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.20 ', N' SCHIZOPHRENIA, CATATONIC', N'295.20 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.30 ', N' SCHIZOPHRENIA, PARANOID', N'295.30 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.40 ', N' SCHIZOPHRENIFORM DIS NOS  ', N'295.40 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.60 ', N' SCHIZOPHRENIA, RESIDUAL', N'295.60 ', 0, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.70 ', N' SCHIZOAFFECTIVE DIS BIPOLAR/DEPRESSED', N'295.70 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'295.90 ', N' SCHIZOPHRENIA, UNDIFFERENTIATED', N'295.90 ', 1, N'Schizophrenic')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.00 ', N' BIPOL 1, SINGLE MANIC EP, UNSPEC', N'296.00 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.01 ', N' BIPOL 1, SINGLE MANIC EP, MILD', N'296.01 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.02 ', N' BIPOL 1, SINGLE MANIC EP, MOD', N'296.02 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.03 ', N' BIPOL 1, SINGLE MANIC EP, SEV W/O PSYCH', N'296.03 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.04 ', N' BIPOL 1, SINGLE MANIC EP, SEV W PSYCH', N'296.04 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.05 ', N' BIPOL 1, SINGLE MANIC EP, PART REMISS', N'296.05 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.06 ', N' BIPOL 1, SINGLE MANIC EP, FULL REMISS', N'296.06 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.20 ', N' MAJ DEPR D/O, SING EP, UNSPEC', N'296.20 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.21 ', N' MAJ DEPR D/O, SING EP, MILD', N'296.21 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.22 ', N' MAJ DEPR D/O, SING EP, MOD', N'296.22 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.23 ', N' MAJ DEPR D/O, SING EP, SEV W/O PSYCH', N'296.23 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.24 ', N' MAJ DEPR D/O, SING EP, SEV W PSYCH', N'296.24 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.25 ', N' MAJ DEPR D/O, SING EP, PART REMISS', N'296.25 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.26 ', N' MAJ DEPR D/O, SING EP, FULL REMISS', N'296.26 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.30 ', N' MAJ DEPR D/O, RECURR, UNSPEC', N'296.30 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.31 ', N' MAJ DEPR D/O, RECURR, MILD', N'296.31 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.32 ', N' MAJ DEPR D/O, RECURR, MOD', N'296.32 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.33 ', N' MAJ DEPR D/O, RECURR, SEV W/O PSYCH', N'296.33 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.34 ', N' MAJ DEPR D/O, RECURR, SEV W PSYCH', N'296.34 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.35 ', N' MAJ DEPR D/O, RECURR, PART REMISS', N'296.35 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.36 ', N' MAJ DEPR D/O, RECURR, FULL REMISS', N'296.36 ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.40 ', N' BIPOL 1, MRE HYPOMANIC', N'296.40 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.41 ', N' BIPOL 1, MRE MANIC, MILD', N'296.41 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.42 ', N' BIPOL 1, MRE MANIC, MOD', N'296.42 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.43 ', N' BIPOL 1, MRE MANIC, SEV W/O PSYCH', N'296.43 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.44 ', N' BIPOL 1, MRE MANIC, SEV W PSYCH', N'296.44 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.45 ', N' BIPOL 1, MRE MANIC, PART REMISS', N'296.45 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.46 ', N' BIPOL 1, MRE MANIC, FULL REMISS', N'296.46 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.50 ', N' BIPOL 1, MRE DEPR, UNSPEC', N'296.50 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.51 ', N' BIPOL 1, MRE DEPR, MILD', N'296.51 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.52 ', N' BIPOL 1, MRE DEPR, MOD', N'296.52 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.53 ', N' BIPOL 1, MRE DEPR, SEV W/O PSYCH', N'296.53 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.54 ', N' BIPOL 1, MRE DEPR, SEV W PSYCH', N'296.54 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.55 ', N' BIPOL 1, MRE DEPR, PART REMISS', N'296.55 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.56 ', N' BIPOL 1, MRE DEPR, FULL REMISS', N'296.56 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.60 ', N' BIPOL 1, MRE MIXED, UNSPEC', N'296.60 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.61 ', N' BIPOL 1, MRE MIXED, MILD', N'296.61 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.62 ', N' BIPOL 1, MRE MIXED, MOD', N'296.62 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.63 ', N' BIPOL 1, MRE MIXED, SEV W/O PSYCH', N'296.63 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.64 ', N' BIPOL 1, MRE MIXED, SEV W PSYCH', N'296.64 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.65 ', N' BIPOL 1, MRE MIXED, PART REMISS', N'296.65 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.66 ', N' BIPOL 1, MRE MIXED, FULL REMISS', N'296.66 ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.7  ', N' BIPOL 1, MRE UNSPECIFIED', N'296.7  ', 1, N'Bipolar Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.80 ', N' BIPOLAR DISORDER NOS', N'296.80 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.89 ', N' BIPOLAR-II DISORDER', N'296.89 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'296.90 ', N' MOOD DISORDER NOS  ', N'296.90 ', 0, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'297.1  ', N' DELUSIONAL DISORDER  ', N'297.1  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'297.3  ', N' SHARED PSYCHOTIC DISORDER', N'297.3  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'298.8  ', N' BRIEF PSYCHOTIC DISORDER', N'298.8  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'298.9  ', N' PSYCHOSIS NOS  ', N'298.9  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'299.00 ', N' AUTISTIC DISORD-CURRENT  ', N'299.00 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'299.1  ', N' CHILDHD DISINTEGR DIS  ', N'299.1  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'299.80 ', N' RETT''S/ASPERGER''S/PERVASIVE DISORDER', N'299.80 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.00 ', N' ANXIETY STATE NOS  ', N'300.00 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.01 ', N' PANIC DIS W/O AGORPHOBIA  ', N'300.01 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.02 ', N' GENERALIZED ANXIETY DIS  ', N'300.02 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.11 ', N' CONVERSION DISORDER  ', N'300.11 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.12 ', N' DISSOCIATIVE AMNESIA  ', N'300.12 ', 1, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.13 ', N' DISSOCIATIVE FUGUE  ', N'300.13 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.14 ', N' DISSOCIATVE IDENTITY DIS  ', N'300.14 ', 1, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.15 ', N' DISSOCIATIVE REACT NOS  ', N'300.15 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.16 ', N' FACTITIOUS DIS W SYMPTOM  ', N'300.16 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.19 ', N' FACTITIOUS ILL NEC/NOS  ', N'300.19 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.21 ', N' PANIC DIS WITH AGORAPHOBIA', N'300.21 ', 0, N'Neurotic Disorders')
GO
print 'Processed 100 total records'
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.22 ', N' AGORAPHOBIA W/O PANIC  ', N'300.22 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.23 ', N' SOCIAL PHOBIA  ', N'300.23 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.29 ', N' SPECIFIC PHOBIA', N'300.29 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.3  ', N' OBSESSIVE-COMPULSIVE DIS  ', N'300.3  ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.4  ', N' DYSTHYMIC DISORDER  ', N'300.4  ', 1, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.6  ', N' DEPERSONALIZATION DISORD  ', N'300.6  ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.7  ', N' HYPOCHONDRIASIS/BODY DYSMORPHIC DISORDER  ', N'300.7  ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.81 ', N' SOMATIZATION DISORDER  ', N'300.81 ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.82 ', N' UNDIFF SOMATOFORM DISRDR  ', N'300.82 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'300.9  ', N' UNSPECIFIED MENTAL DIS (NONPSYCHOTIC)', N'300.9  ', 0, N'Neurotic Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'301.13 ', N' CYCLOTHYMIC DISORDER  ', N'301.13 ', 1, N'Personality Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.2  ', N' PEDOPHILIA  ', N'302.2  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.3  ', N' TRANSVESTIC FETISHISM  ', N'302.3  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.4  ', N' EXHIBITIONISM  ', N'302.4  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.6  ', N' GENDR IDENTITY DIS-CHILD  ', N'302.6  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.70 ', N' PSYCHOSEXUAL DYSFUNC NOS  ', N'302.70 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.71 ', N' HYPOACTIVE SEX DESIRE  ', N'302.71 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.72 ', N' INHIBITED SEX EXCITEMENT  ', N'302.72 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.73 ', N' FEMALE ORGASMIC DISORDER  ', N'302.73 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.74 ', N' MALE ORGASMIC DISORDER  ', N'302.74 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.75 ', N' PREMATURE EJACULATION  ', N'302.75 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.76 ', N' DYSPAREUNIA,PSYCHOGENIC  ', N'302.76 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.79 ', N' SEXUAL AVERSION DISORDER', N'302.79 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.81 ', N' FETISHISM  ', N'302.81 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.82 ', N' VOYEURISM  ', N'302.82 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.83 ', N' SEXUAL MASOCHISM  ', N'302.83 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.84 ', N' SEXUAL SADISM  ', N'302.84 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.85 ', N' GEND IDEN DIS,ADOL/ADULT  ', N'302.85 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.89 ', N' FROTTEURISM', N'302.89 ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'302.9  ', N' PARAPHILIA NOS', N'302.9  ', 0, N'Sexual Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'303.00 ', N' AC ALCOHOL INTOX-UNSPEC  ', N'303.00 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'303.90 ', N' ALCOH DEP NEC/NOS-UNSPEC  ', N'303.90 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'303.93 ', N' ALCOH DEP NEC/NOS-REMISS  ', N'303.93 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.00 ', N' OPIOID DEPENDENCE-UNSPEC  ', N'304.00 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.03 ', N' OPIOID DEPENDENCE-REMISS  ', N'304.03 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.10 ', N' SED,HYP,ANXIOLYT DEP-NOS  ', N'304.10 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.13 ', N' SED,HYP,ANXIOLYT DEP-REM  ', N'304.13 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.20 ', N' COCAINE DEPEND-UNSPEC  ', N'304.20 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.23 ', N' COCAINE DEPEND-REMISS  ', N'304.23 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.30 ', N' CANNABIS DEPEND-UNSPEC  ', N'304.30 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.33 ', N' CANNABIS DEPEND-REMISS  ', N'304.33 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.40 ', N' AMPHETAMIN DEPEND-UNSPEC  ', N'304.40 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.43 ', N' AMPHETAMIN DEPEND-REMISS  ', N'304.43 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.50 ', N' HALLUCINOGEN DEP-UNSPEC  ', N'304.50 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.53 ', N' HALLUCINOGEN DEP-REMISS  ', N'304.53 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.60 ', N' DRUG DEPEND NEC-UNSPEC  ', N'304.60 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.63 ', N' DRUG DEPEND NEC-IN REM  ', N'304.63 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.80 ', N' POLYSUBSTANCE DEPENDENCE', N'304.80 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.83 ', N' POLYSUBSTANCE DEPENDENCE, IN REMISSION', N'304.83 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'304.90 ', N' DRUG DEPEND NOS-UNSPEC  ', N'304.90 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.00 ', N' ALCOHOL ABUSE-UNSPEC  ', N'305.00 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.03 ', N' ALCOHOL ABUSE-IN REMISS  ', N'305.03 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.1  ', N' TOBACCO USE DISORDER  ', N'305.1  ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.20 ', N' CANNABIS ABUSE-UNSPEC  ', N'305.20 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.23 ', N' CANNABIS ABUSE-IN REMISS  ', N'305.23 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.30 ', N' HALLUCINOG ABUSE-UNSPEC  ', N'305.30 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.33 ', N' HALLUCINOG ABUSE-REMISS  ', N'305.33 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.40 ', N' SED,HYP,ANXIOLYTC AB-NOS  ', N'305.40 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.43 ', N' SED,HYP,ANXIOLYTC AB-REM  ', N'305.43 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.50 ', N' OPIOID ABUSE-UNSPEC  ', N'305.50 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.53 ', N' OPIOID ABUSE-IN REMISS  ', N'305.53 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.60 ', N' COCAINE ABUSE-UNSPEC  ', N'305.60 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.63 ', N' COCAINE ABUSE-IN REMISS  ', N'305.63 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.70 ', N' AMPHETAMINE ABUSE-UNSPEC  ', N'305.70 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.73 ', N' AMPHETAMINE ABUSE-REMISS  ', N'305.73 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.90 ', N' DRUG ABUSE NEC-UNSPEC  ', N'305.90 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'305.93 ', N' DRUG ABUSE NEC-IN REMISS  ', N'305.93 ', 0, N'Substance Abuse')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'306.51 ', N' PSYCHOGENIC VAGINISMUS  ', N'306.51 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.0  ', N' STUTTERING  ', N'307.0  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.1  ', N' ANOREXIA NERVOSA  ', N'307.1  ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.20 ', N' TIC DISORDER NOS  ', N'307.20 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.21 ', N' TRANSIENT TIC DISORDER  ', N'307.21 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.22 ', N' CHR MOTOR/VOCAL TIC DIS  ', N'307.22 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.23 ', N' TOURETTE''S DISORDER  ', N'307.23 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.3  ', N' STEREOTYPIC MOVEMENT DIS  ', N'307.3  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.42 ', N' PERSISTENT INSOMNIA  ', N'307.42 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.44 ', N' PERSISTENT HYPERSOMNIA  ', N'307.44 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.45 ', N' CIRCADIAN RHYTHM SLEEP  ', N'307.45 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.46 ', N' SLEEP TERROR/SLEEPWALKING DISORDER  ', N'307.46 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.47 ', N' DYSSOMNIA/PARASOMNIA NOS/NIGHTMARE DISORDER', N'307.47 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.50 ', N' EATING DISORDER NOS  ', N'307.50 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.51 ', N' BULIMIA NERVOSA  ', N'307.51 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.52 ', N' PICA  ', N'307.52 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.53 ', N' RUMINATION DISORDER  ', N'307.53 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.59 ', N' FEEDING DIS OF INFANCY/CHILD', N'307.59 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.6  ', N' ENURESIS  ', N'307.6  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.7  ', N' ENCOPRESIS  ', N'307.7  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.8  ', N' PSYCHOGENIC PAIN DISORD  ', N'307.8  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.80 ', N' PSYCHOGENIC PAIN NOS  ', N'307.80 ', 1, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'307.9  ', N' COMMUNICATION DISORDER NOS', N'307.9  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'308.3  ', N' ACUTE STRESS REACT NEC  ', N'308.3  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.0  ', N' ADJUSTMNT DIS W DEPRESSN  ', N'309.0  ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.21 ', N' SEPARATION ANXIETY  ', N'309.21 ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.24 ', N' ADJUSTMENT DIS W ANXIETY  ', N'309.24 ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.28 ', N' ADJUST DIS W ANXIETY/DEP  ', N'309.28 ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.3  ', N' ADJUST DISOR/DIS CONDUCT  ', N'309.3  ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.4  ', N' ADJ DIS-EMOTION/CONDUCT  ', N'309.4  ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.81 ', N' POSTTRAUMATIC STRESS DIS  ', N'309.81 ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'309.9  ', N' ADJUSTMENT REACTION NOS  ', N'309.9  ', 0, N'Adjustment Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'311  ', N' DEPRESSIVE DISORDER NOS', N'311  ', 1, N'Mood Disorders')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.30 ', N' IMPULSE CONTROL DIS NOS  ', N'312.30 ', 0, N'Childhood Disorder')
GO
print 'Processed 200 total records'
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.31 ', N' PATHOLOGICAL GAMBLING  ', N'312.31 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.32 ', N' KLEPTOMANIA  ', N'312.32 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.33 ', N' PYROMANIA  ', N'312.33 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.34 ', N' INTERMITT EXPLOSIVE DIS  ', N'312.34 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.39 ', N' TRICHTILLOMANIA', N'312.39 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.81 ', N' CNDCT DSRDR CHLDHD ONST  ', N'312.81 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.82 ', N' CNDCT DSRDR ADLSCNT ONST  ', N'312.82 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.89 ', N' OTHER CONDUCT DISORDER  ', N'312.89 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'312.9  ', N' CONDUCT DISTURBANCE NOS  ', N'312.9  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'313.23 ', N' SELECTIVE MUTISM  ', N'313.23 ', 1, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'313.81 ', N' OPPOSITION DEFIANT DISOR  ', N'313.81 ', 1, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'313.89 ', N' REACTIVE ATTACHMENT DIS', N'313.89 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'313.9  ', N' EMOTIONAL DIS CHILD NOS  ', N'313.9  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'314.00 ', N' ADHD, INATTENTIVE TYPE', N'314.00 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'314.01 ', N' ADHD, HYPERACTIVE/IMPULSIVE/COMBINED TYPE', N'314.01 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'314.9  ', N' ADHD, NOS', N'314.9  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.00 ', N' READING DISORDER NOS  ', N'315.00 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.1  ', N' MATHEMATICS DISORDER  ', N'315.1  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.2  ', N' DISORDER OF WRITTEN EXPRESSION', N'315.2  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.31 ', N' EXPRESSIVE LANGUAGE DIS  ', N'315.31 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.32 ', N' RECP-EXPRES LANGUAGE DIS  ', N'315.32 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.39 ', N' PHONOLOGICAL DISORDER', N'315.39 ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.4  ', N' DEVEL COORDINATION DIS  ', N'315.4  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'315.9  ', N' LEARNING DISORDER NOS', N'315.9  ', 0, N'Childhood Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'316  ', N' PSYCHIC FACTOR W OTH DIS  ', N'316  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.10 ', N' CONSL PARTNER PROB  ', N'V61.10 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.11 ', N' CNSL VICTM PARTNER ABUSE  ', N'V61.11 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.12 ', N' CNSL PERP PARTNER ABUSE  ', N'V61.12 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.20 ', N' CNSL PRNT-CHLD PROB NOS  ', N'V61.20 ', 0, N'Family Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.21 ', N' CNSL VICTIM CHILD ABUSE  ', N'V61.21 ', 0, N'Family Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.8  ', N' SIBLING RELATIONAL PROB', N'V61.8  ', 0, N'Family Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V61.9  ', N' FAMILY CIRCUMSTANCE NOS  ', N'V61.9  ', 0, N'Family Disorder')
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.2  ', N' OCCUP CIRCUMSTANCES NEC  ', N'V62.2  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.3  ', N' EDUCATIONAL CIRCUMSTANCE  ', N'V62.3  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.4  ', N' SOCIAL MALADJUSTMENT  ', N'V62.4  ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.81 ', N' INTERPERSONAL PROBL NEC  ', N'V62.81 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.82 ', N' BEREAVEMENT, UNCOMPLICAT  ', N'V62.82 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V62.89 ', N' PSYCHOLOGICAL STRESS NEC  ', N'V62.89 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V71.01 ', N' OBSV-ADULT ANTISOC BEHAV  ', N'V71.01 ', 0, NULL)
INSERT [dbo].[DiagnosisAxis1] ([ICD9], [Description], [DSMIV], [508k], [TabDiagnosis]) VALUES (N'V71.02 ', N' OBSV-ADOLESC ANTISOC BEH  ', N'V71.02 ', 0, NULL)
/****** Object:  Table [dbo].[Codes]    Script Date: 05/25/2010 14:13:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Codes](
	[code_id] [bigint] IDENTITY(1,1) NOT NULL,
	[column_name] [varchar](20) NULL,
	[value] [varchar](3) NULL,
	[descriptor] [varchar](60) NULL,
 CONSTRAINT [PK_Codes] PRIMARY KEY CLUSTERED 
(
	[code_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Codes] ON
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (1, N'CaseStatus', N'A', N'Active')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (2, N'CaseStatus', N'D', N'Terminated')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (3, N'CaseStatus', N'P', N'Pre-Admit')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (4, N'Gender', N'M', N'Male')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (5, N'Gender', N'F', N'Female')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (6, N'Suffix', N'II', N'II')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (7, N'Suffix', N'III', N'III')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (8, N'Suffix', N'JR', N'JR')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (9, N'Suffix', N'SR', N'SR')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (10, N'MaritalStatus', N'S', N'Single (Never Married)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (11, N'MaritalStatus', N'D', N'Divorced')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (12, N'MaritalStatus', N'M', N'Married')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (13, N'MaritalStatus', N'O', N'Other')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (14, N'MaritalStatus', N'W', N'Widowed')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (17, N'LivingArrangement', N'A', N'Own Home')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (18, N'LivingArrangement', N'B', N'Friend''s Home')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (19, N'LivingArrangement', N'C', N'Relative''s Home')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (20, N'LivingArrangement', N'D', N'Supervised Group Living')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (21, N'LivingArrangement', N'E', N'Supervised Apartment')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (22, N'LivingArrangement', N'F', N'Boarding Home')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (23, N'LivingArrangement', N'G', N'Crisis Residential')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (24, N'LivingArrangement', N'H', N'Child Foster Care')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (25, N'LivingArrangement', N'I', N'Adult Foster Care')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (26, N'LivingArrangement', N'J', N'Intermediate Care Facility')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (27, N'LivingArrangement', N'K', N'Skilled Nursing Facility')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (28, N'LivingArrangement', N'L', N'Resite Care')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (29, N'LivingArrangement', N'M', N'Mental Retardation Intermediate Care Facility')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (30, N'LivingArrangement', N'N', N'Licensed Mental Retardation Intermediate Care Facililty')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (31, N'LivingArrangement', N'O', N'State Mental Retardation Institution')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (32, N'LivingArrangement', N'P', N'State Mental Health Institution')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (33, N'LivingArrangement', N'Q', N'Hospital')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (34, N'LivingArrangement', N'R', N'Correctional Facility')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (35, N'LivingArrangement', N'S', N'Homeless')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (36, N'LivingArrangement', N'T', N'Rest Home')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (37, N'LivingArrangement', N'U', N'Other')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (38, N'EducationLevel', N'00', N'Less than Grade 1')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (39, N'EducationLevel', N'01', N'Grade 1')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (40, N'EducationLevel', N'02', N'Grade 2')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (41, N'EducationLevel', N'03', N'Grade 3')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (42, N'EducationLevel', N'04', N'Grade 4')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (43, N'EducationLevel', N'05', N'Grade 5')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (44, N'EducationLevel', N'06', N'Grade 6')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (45, N'EducationLevel', N'07', N'Grade 7')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (46, N'EducationLevel', N'08', N'Grade 8')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (47, N'EducationLevel', N'09', N'Grade 9')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (48, N'EducationLevel', N'10', N'Grade 10')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (49, N'EducationLevel', N'11', N'Grade 11')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (50, N'EducationLevel', N'12', N'High School Diploma/GED')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (51, N'EducationLevel', N'13', N'Trade or Technical School')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (52, N'EducationLevel', N'14', N'Some College')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (53, N'EducationLevel', N'15', N'2 Year College/Associate Degree')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (54, N'EducationLevel', N'16', N'4 Year College/Undergrad. Degree')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (55, N'EducationLevel', N'17', N'Graduate Courses')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (56, N'EducationLevel', N'18', N'Graduate Degree')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (57, N'EducationLevel', N'19', N'Post-Graduate Studies')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (58, N'EducationLevel', N'20', N'Further Specialized Studies')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (59, N'EducationType', N'1', N'Regular')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (60, N'EducationType', N'2', N'Severe Behavioral Handicaped (SBH)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (61, N'EducationType', N'3', N'Learning Disabled (LD)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (62, N'EducationType', N'4', N'Hearing Impaired (HI)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (63, N'EducationType', N'5', N'Visually Impaired (VI)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (64, N'EducationType', N'6', N'Multihandicapped')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (65, N'EducationType', N'7', N'Developmentally Disabled (DD)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (66, N'EducationType', N'8', N'Orthopedically Handicapped (OH)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (67, N'EducationType', N'9', N'Other')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (68, N'Employment', N'A', N'Full Time (35 Hrs+ per week)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (69, N'Employment', N'B', N'Part Time (<35 Hrs per week)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (70, N'Employment', N'C', N'Sheltered Employment')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (71, N'Employment', N'D', N'Unemployed')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (72, N'Employment', N'E', N'Homemaker')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (73, N'Employment', N'F', N'Student')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (74, N'Employment', N'G', N'Retired')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (75, N'Employment', N'H', N'Disabled')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (76, N'Employment', N'I', N'Inmate of Institution')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (77, N'Employment', N'J', N'Other')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (78, N'IncomeSource', N'A', N'Wages/Salary Income')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (79, N'IncomeSource', N'B', N'Family or Relative')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (80, N'IncomeSource', N'C', N'Alimony')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (81, N'IncomeSource', N'D', N'Child Support')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (82, N'IncomeSource', N'E', N'Savings or Investments')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (83, N'IncomeSource', N'F', N'Disability Insurance/Worker''s Compensation')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (84, N'IncomeSource', N'G', N'Unemployment Compensation')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (85, N'IncomeSource', N'H', N'Retirement Pension')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (86, N'IncomeSource', N'I', N'Social Security Retirement')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (87, N'IncomeSource', N'J', N'General Relief/Welfare')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (88, N'IncomeSource', N'K', N'Aid for a Dependent Child (ADC or AFD)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (89, N'IncomeSource', N'L', N'Supplemental Security Income (SSI)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (90, N'IncomeSource', N'M', N'Social Security Disability Insurance (SSDI)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (91, N'IncomeSource', N'N', N'Other')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (92, N'IncomeSource', N'P', N'None')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (93, N'Race', N'A', N'Asian')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (94, N'Race', N'B', N'Black or African American')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (95, N'Race', N'M', N'Alaskan Native')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (96, N'Race', N'N', N'American Indian')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (97, N'Race', N'P', N'Hispanic')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (98, N'Race', N'U', N'Unknown')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (99, N'Race', N'W', N'White')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (100, N'LevelofCare', N'A1', N'Consultation and Early Intervention (Level 0.5)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (101, N'LevelofCare', N'B1', N'Outpatient Services (Level I-A)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (102, N'LevelofCare', N'B2', N'Intensive Outpatient Services (Level I-B)')
GO
print 'Processed 100 total records'
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (103, N'LevelofCare', N'B3', N'Day Treatment (Level I-C)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (104, N'LevelofCare', N'C1', N'Non-Medical Community Residential Treatment (Level II-A)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (105, N'LevelofCare', N'C2', N'Medical Community Residential (Level II-B)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (106, N'LevelofCare', N'D1', N'Ambulatory Detoxification (Level III-A)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (107, N'LevelofCare', N'D2', N'23-Hours Observation Bed (Level III-B)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (108, N'LevelofCare', N'D3', N'Sub-Acute Detoxification (Level IV)')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (109, N'LevelofCare', N'NA', N'Not Applicable')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (110, N'County', NULL, N'Adams')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (111, N'County', NULL, N'Butler')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (112, N'County', NULL, N'Champaign')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (113, N'County', NULL, N'Clark')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (114, N'County', NULL, N'Clermont')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (115, N'County', NULL, N'Clinton')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (116, N'County', NULL, N'Darke')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (117, N'County', NULL, N'Defiance')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (118, N'County', NULL, N'Fairfield')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (119, N'County', NULL, N'Franklin')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (120, N'County', NULL, N'Greene')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (121, N'County', NULL, N'Hamilton')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (122, N'County', NULL, N'Licking')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (123, N'County', NULL, N'Lucas')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (124, N'County', NULL, N'Marion')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (125, N'County', NULL, N'Miami')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (126, N'County', NULL, N'Monroe')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (127, N'County', NULL, N'Montgomery')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (128, N'County', NULL, N'Morrow')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (129, N'County', NULL, N'Paulding')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (130, N'County', NULL, N'Preble')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (131, N'County', NULL, N'Richland')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (132, N'County', NULL, N'Shelby')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (133, N'County', NULL, N'Trumbull')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (134, N'County', NULL, N'Tuscarawas')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (135, N'County', NULL, N'Union')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (136, N'County', NULL, N'Unknown')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (137, N'County', NULL, N'Warren')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (139, N'Disposition', N'1', N'Open')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (140, N'Disposition', N'0', N'Closed')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (141, N'MessageType', N'1', N'Referral Message')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (142, N'MessageType', N'2', N'Case Management Message')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (143, N'MessageType', N'3', N'Therapy Message')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (144, N'MessageType', N'4', N'Medication Message')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (145, N'MessageType', N'5', N'Bulletin Message')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (146, N'MessageType', N'6', N'Triage Message')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (147, N'AddressType', NULL, N'Main')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (148, N'AddressType', NULL, N'Emergency')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (149, N'AddressType', NULL, N'Guardian')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (150, N'AddressType', NULL, N'Employer')
INSERT [dbo].[Codes] ([code_id], [column_name], [value], [descriptor]) VALUES (151, N'EducationLevel', N'N/A', N'21')
SET IDENTITY_INSERT [dbo].[Codes] OFF